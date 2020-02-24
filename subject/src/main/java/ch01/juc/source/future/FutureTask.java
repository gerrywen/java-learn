package ch01.juc.source.future;

import java.util.concurrent.*;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.locks.LockSupport;

/**
 * @author wenguoli
 * @description: FutureTask 深度解析
 * https://blog.csdn.net/liulipuo/article/details/39029643
 *
 * sun.misc.Unsafe操作手册
 * https://blog.csdn.net/u013256816/article/details/84112878
 * @date 2020/2/23 0023 21:56
 */
public class FutureTask<V> implements RunnableFuture<V> {


    /**
     * 状态仅有一下几种变化情况：
     * 1、NEW -> COMPLETING -> NORMAL（正常执行完成）
     * 2、NEW -> COMPLETING -> EXCEPTIONAL（执行期间有未捕获异常）
     * 3、NEW -> CANCELLED（任务尚未执行，被取消）
     * 4、NEW -> INTERRUPTING -> INTERRUPTED（任务被中断，在执行完成之前）
     */
    private volatile int state;                     // 任务状态
    private static final int NEW = 0;               // 新建    任务尚未开始或处于执行期间
    private static final int COMPLETING = 1;        // 执行中  任务即将执行完成
    private static final int NORMAL = 2;            // 正常    任务执行完毕
    private static final int EXCEPTIONAL = 3;       // 异常    任务执行期间出现未捕获异常
    private static final int CANCELLED = 4;         // 取消    任务被取消
    private static final int INTERRUPTING = 5;      // 中断中   任务正在被中断
    private static final int INTERRUPTED = 6;       // 中断中   任务已被中断

    /**
     * 任务对象
     */
    private Callable<V> callable;
    /**
     * 保存任务返回值(或任务执行期间未捕获的异常)
     */
    private Object outcome; // non-volatile, protected by state reads/writes
    /**
     * 正在运行任务的线程
     * runner变量保存了正在执行任务的线程，如果runner不为null代表有线程正在执行任务
     */
    private volatile Thread runner;
    /**
     * 等待获取任务结果的线程队列(头结点)
     */
    private volatile WaitNode waiters;

    /**
     * Returns result or throws exception for completed task.
     *
     * @param s completed state value
     */
    @SuppressWarnings("unchecked")
    private V report(int s) throws ExecutionException {
        Object x = outcome;
        //如果任务正常执行完成直接返回结果
        if (s == NORMAL)
            return (V) x;
        //如果任务被取消或被中断抛出CancellationException（运行时异常的子类）
        if (s >= CANCELLED)
            throw new CancellationException();
        //如果任务有未捕获的异常则将异常包装到ExecutionException并抛出
        throw new ExecutionException((Throwable) x);
    }

    /**
     * 传入一个Callable任务
     */
    public FutureTask(Callable<V> callable) {
        if (callable == null)
            throw new NullPointerException();
        this.callable = callable;
        this.state = NEW;       // 使用volatile修饰，确保callable的可见性
    }

    /**
     * 传入一个Runnable和返回结果(任务完成后调用get方法的返回值)
     */
    public FutureTask(java.lang.Runnable runnable, V result) {
        // 调用Executors的callable方法将其包装为一个Callable类
        this.callable = Executors.callable(runnable, result);
        this.state = NEW;       // 使用volatile修饰，确保callable的可见性
    }

    public boolean isCancelled() {
        return state >= CANCELLED;
    }

    public boolean isDone() {
        return state != NEW;
    }

    /**
     * cancel方法可以传入一个boolean参数，表示即使任务在运行是否应当中断(interrupt)它，该方法返回任务是否成功被中断
     *
     * 对于mayInterruptIfRunning为true的情况（即使线程正在运行也尝试进行中断）：
     * 1、如果任务状态处于NEW（任务尚未执行完成），那么尝试通过CAS将state由NEW设为INTERRUPTING（正在中断状态），如果设置失败，那么方法返回false，代表中断任务失败。
     * 2、获取执行任务的线程对象，调用其interrupt方法，最后将state设为INTERRUPTED
     * 3、激活所有等待队列中的线程（尝试通过get方法获取返回结果但是被阻塞的线程）
     *
     * 对于mayInterruptIfRunning为false的情况：
     * 1、如果任务状态处于NEW（任务尚未执行完成），那么尝试通过CAS将state由NEW设为CANCELLED（任务取消状态），如果设置失败，那么方法返回false，代表中断任务失败。
     * 2、激活所有等待队列中的线程（尝试通过get方法获取返回结果但是被阻塞的线程）
     * @param mayInterruptIfRunning
     * @return
     */
    public boolean cancel(boolean mayInterruptIfRunning) {
        //如果任务状态为NEW并且成功通过CAS将state状态由NEW改为INTERRUPTING或CANCELLED（视参数而定）
        //那么方法继续执行，否则返回false
        if (!(state == NEW &&
                UNSAFE.compareAndSwapInt(this, stateOffset, NEW,
                        mayInterruptIfRunning ? INTERRUPTING : CANCELLED)))
            return false;
        try {    // in case call to interrupt throws exception
            if (mayInterruptIfRunning) {
                try {
                    //获取执行run方法的线程(执行任务的线程)
                    Thread t = runner;
                    //调用interrupt中断
                    if (t != null)
                        t.interrupt();
                } finally { // final state
                    //将state状态设为INTERRUPTED(已中断)
                    UNSAFE.putOrderedInt(this, stateOffset, INTERRUPTED);
                }
            }
        } finally {
            //激活所有在等待队列中的线程
            finishCompletion();
        }
        return true;
    }

    /**
     * @throws CancellationException {@inheritDoc}
     */
    public V get() throws InterruptedException, ExecutionException {
        int s = state;
        //如果任务尚未执行完成，调用awaitDone使当前线程等待
        if (s <= COMPLETING)
            s = awaitDone(false, 0L);
        //任务执行完成后，调用report返回执行结果或抛出异常
        return report(s);
    }

    /**
     * @throws CancellationException {@inheritDoc}
     */
    public V get(long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException {
        if (unit == null)
            throw new NullPointerException();
        int s = state;
        //如果任务没有完成那么调用awaitDone使当前线程等待，如果超时任务依然没有完成抛出TimeoutException
        if (s <= COMPLETING &&
                (s = awaitDone(true, unit.toNanos(timeout))) <= COMPLETING)
            throw new TimeoutException();
        //任务执行完成后，调用report返回执行结果或抛出异常
        return report(s);
    }

    /**
     * Protected method invoked when this task transitions to state
     * {@code isDone} (whether normally or via cancellation). The
     * default implementation does nothing.  Subclasses may override
     * this method to invoke completion callbacks or perform
     * bookkeeping. Note that you can query status inside the
     * implementation of this method to determine whether this task
     * has been cancelled.
     *
     * done方法非final的protected权限方法，默认实现为空，供子类重写，用来实现激活所有等待线程之后的后续操作。
     */
    protected void done() {
    }

    /**
     * FutureTask的state状态都是由sun.misc.Unsafe类中的CAS相关方法进行设置的，
     * 目的是在不加锁的情况下防止有多个线程尝试进行修改这个state变量，
     * 只允许在多线程竞争情况下有1个线程能够修改成功。这些方法都是本地方法，由C++代码实现
     */
    protected void set(V v) {
        // 通过CAS将NEW设为COMPLETING(即将完成)状态
        // 偏移量 state , 原本状态 NEW ，期望状态 COMPLETING
        if (UNSAFE.compareAndSwapInt(this, stateOffset, NEW, COMPLETING)) {
            // 将任务的返回值设置为
            outcome = v;
            // public native void putOrderedInt(Object obj, long offset, int value);
            // 设置obj对象中offset偏移地址对应的整型field的值为指定值。
            // 这是一个有序或者有延迟的putIntVolatile方法，并且不保证值的改变被其他线程立即看到。
            // 只有在field被volatile修饰并且期望被意外修改的时候使用才有用。
            UNSAFE.putOrderedInt(this, stateOffset, NORMAL); // 最终状态
            finishCompletion();
        }
    }

    /**
     * 如果任务执行期间有未捕获的异常，那么会调用以下方法设置异常
     */
    protected void setException(Throwable t) {
        // 通过CAS将state由NEW设为COMPLETING
        if (UNSAFE.compareAndSwapInt(this, stateOffset, NEW, COMPLETING)) {
            // 将异常对象赋给outcome实例变量
            outcome = t;
            // 将state设为EXCEPTIONAL（有异常抛出状态）
            UNSAFE.putOrderedInt(this, stateOffset, EXCEPTIONAL); // final state
            // 激活所有等待队列中的线程
            finishCompletion();
        }
    }

    /**
     * 这个run方法是Runnable接口要求实现的，由线程对象负责调用此方法来异步执行
     */
    public void run() {
        //如果状态不属于NEW，那么通过CAS将runner变量由null设为当前线程，
        //如果设置失败方法返回（保证只有1个线程执行run方法）
        // 偏移 runner， 原本值 null, 期望值 当前线程
        if (state != NEW ||
                !UNSAFE.compareAndSwapObject(this, runnerOffset,
                        null, Thread.currentThread()))
            return;
        try {
            // 获取构造时传入的Callable任务对象
            Callable<V> c = callable;
            // 如果状态为NEW
            if (c != null && state == NEW) {
                V result;
                //任务是否正常完成
                boolean ran;
                try {
                    //调用Callable的call方法执行任务
                    result = c.call();
                    //如果没有未捕获的异常
                    ran = true;
                } catch (Throwable ex) { //如果任务执行期间有未捕获异常导致任务中断
                    //没有返回值
                    result = null;
                    ran = false;
                    //设置异常对象，由调用get方法的线程处理这个异常
                    setException(ex);
                }
                //如果任务正常结束则设置返回值和state变量
                if (ran)
                    set(result);
            }
        } finally {
            // runner must be non-null until state is settled to
            // prevent concurrent calls to run()
            runner = null;
            // state must be re-read after nulling runner to prevent
            // leaked interrupts
            // 获取state状态
            int s = state;
            //如果处于任务正在中断状态，则等待直到任务处于已中断状态位置
            if (s >= INTERRUPTING)
                handlePossibleCancellationInterrupt(s);
        }
    }

    /**
     * 如果当前任务需要执行多次，那么需要调用runAndReset方法，
     * 该方法在执行任务完成后不会设置outcome变量，也不会激活等待的线程。
     */
    protected boolean runAndReset() {
        //和run方法类似，通过CAS操作将成员变量runner设置为当前线程
        if (state != NEW ||
                !UNSAFE.compareAndSwapObject(this, runnerOffset,
                        null, Thread.currentThread()))
            return false;
        boolean ran = false;
        int s = state;
        try {
            Callable<V> c = callable;
            if (c != null && s == NEW) {
                try {
                    //不会调用set方法设置返回值(outcome成员变量)
                    c.call(); // don't set result
                    ran = true;
                } catch (Throwable ex) {
                    setException(ex);
                }
            }
        } finally {
            // runner must be non-null until state is settled to
            // prevent concurrent calls to run()
            runner = null;
            // state must be re-read after nulling runner to prevent
            // leaked interrupts
            s = state;
            if (s >= INTERRUPTING)
                handlePossibleCancellationInterrupt(s);
        }
        //这个方法不会修改state变量的值
        return ran && s == NEW;
    }

    /**
     * 在run方法最后，会检测state是否为INTERRUPTING状态（任务中断状态），
     * 如果处于该状态则会调用handlePossibleCancellationInterrupt方法，该方法会等待state为INTERRUPTED为止
     */
    private void handlePossibleCancellationInterrupt(int s) {
        // It is possible for our interrupter to stall before getting a
        // chance to interrupt us.  Let's spin-wait patiently.
        if (s == INTERRUPTING)
            // 等待到INTERRUPTED时跳出循环
            while (state == INTERRUPTING)
                // Java线程中的Thread.yield( )方法，译为线程让步。顾名思义，就是说当一个线程使用了这个方法之后，它就会把自己CPU执行的时间让掉，
                //让自己或者其它的线程运行，注意是让自己或者其他线程运行，并不是单纯的让给其他线程。
                //  yield()的作用是让步。它能让当前线程由“运行状态”进入到“就绪状态”，从而让其它具有相同优先级的等待线程获取执行权；但是，并不能保
                //证在当前线程调用yield()之后，其它具有相同优先级的线程就一定能获得执行权；也有可能是当前线程又进入到“运行状态”继续运行！
                Thread.yield(); // wait out pending interrupt

        // assert state == INTERRUPTED;

        // We want to clear any interrupt we may have received from
        // cancel(true).  However, it is permissible to use interrupts
        // as an independent mechanism for a task to communicate with
        // its caller, and there is no way to clear only the
        // cancellation interrupt.
        //
        // Thread.interrupted();
    }

    /**
     * 等待队列在FutureTask中是一个单向链表，链表的结点为内部类WaitNode
     * WaitNode保存了等待线程对象和维护链表的后驱指针。
     */
    static final class WaitNode {
        volatile Thread thread;
        volatile WaitNode next;

        WaitNode() {
            thread = Thread.currentThread(); // 当前线程
        }
    }

    /**
     * Removes and signals all waiting threads, invokes done(), and
     * nulls out callable.
     */
    private void finishCompletion() {
        // assert state > COMPLETING;
        // 不断获取队首
        for (WaitNode q; (q = waiters) != null; ) {
            // 通过CAS删除队列头部
            if (UNSAFE.compareAndSwapObject(this, waitersOffset, q, null)) {
                for (; ; ) {
                    // 获取队列结点上的等待线程
                    Thread t = q.thread;
                    if (t != null) {
                        q.thread = null;
                        //激活该线程
                        LockSupport.unpark(t);
                    }
                    // 如果已经达到队列尾部跳出循环
                    WaitNode next = q.next;
                    if (next == null)
                        break;
                    q.next = null; // unlink to help gc
                    q = next;
                }
                break;
            }
        }

        done();
        //删除任务对象引用
        callable = null;        // to reduce footprint
    }

    /**
     * Awaits completion or aborts on interrupt or timeout.
     *
     * @param timed true if use timed waits
     * @param nanos time to wait, if timed
     * @return state upon completion
     */
    private int awaitDone(boolean timed, long nanos)
            throws InterruptedException {
        // 获取超时的时间戳
        final long deadline = timed ? System.nanoTime() + nanos : 0L;
        WaitNode q = null;
        //是否成功入队
        boolean queued = false;
        //自旋操作
        for (; ; ) {
            //如果线程中断
            if (Thread.interrupted()) {
                //移除这个结点并抛出异常
                removeWaiter(q);
                throw new InterruptedException();
            }

            int s = state;
            //如果任务执行结束或被取消(中断)，方法结束
            if (s > COMPLETING) {
                if (q != null)
                    q.thread = null;
                return s;
            }
            //如果任务即将完成，让当前线程让步
            else if (s == COMPLETING) // cannot time out yet
                Thread.yield();
            else if (q == null)
                q = new WaitNode();
            //如果没有入队，将这个WaitNode加入到FutureTask的等待队列尾部
            else if (!queued)
                queued = UNSAFE.compareAndSwapObject(this, waitersOffset,
                        q.next = waiters, q);
            //如果设置了超时时间
            else if (timed) {
                nanos = deadline - System.nanoTime();
                //如果超时，那么从等待队列中移除这个结点，方法结束
                if (nanos <= 0L) {
                    removeWaiter(q);
                    return state;
                }
                //没有超时则阻塞当前线程nanos纳秒
                LockSupport.parkNanos(this, nanos);
            } else
                //阻塞当前线程
                LockSupport.park(this);
        }
    }

    /**
     * Tries to unlink a timed-out or interrupted wait node to avoid
     * accumulating garbage.  Internal nodes are simply unspliced
     * without CAS since it is harmless if they are traversed anyway
     * by releasers.  To avoid effects of unsplicing from already
     * removed nodes, the list is retraversed in case of an apparent
     * race.  This is slow when there are a lot of nodes, but we don't
     * expect lists to be long enough to outweigh higher-overhead
     * schemes.
     */
    private void removeWaiter(WaitNode node) {
        if (node != null) {
            node.thread = null;
            retry:
            for (; ; ) {          // restart on removeWaiter race
                for (WaitNode pred = null, q = waiters, s; q != null; q = s) {
                    s = q.next;
                    if (q.thread != null)
                        pred = q;
                    else if (pred != null) {
                        pred.next = s;
                        if (pred.thread == null) // check for race
                            continue retry;
                    } else if (!UNSAFE.compareAndSwapObject(this, waitersOffset,
                            q, s))
                        continue retry;
                }
                break;
            }
        }
    }

    /**
     * FutureTask所有方法都是线程安全的，它没有通过加锁来实现线程安全，
     * 而是通过sun.misc.Unsafe类中的CAS方法来实现
     */
    private static final sun.misc.Unsafe UNSAFE;
    private static final long stateOffset;      // state成员变量的内存偏移量
    private static final long runnerOffset;     // runner成员变量的内存偏移量
    private static final long waitersOffset;    // waiters成员变量的内存偏移量

    static {
        try {
            UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class<?> k = FutureTask.class;
            stateOffset = UNSAFE.objectFieldOffset
                    (k.getDeclaredField("state"));
            runnerOffset = UNSAFE.objectFieldOffset
                    (k.getDeclaredField("runner"));
            waitersOffset = UNSAFE.objectFieldOffset
                    (k.getDeclaredField("waiters"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
