package ch01.lock.aqs.jucdemo.lock;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/2/18 10:39
 */
// 自己实现锁
public class NeteaseLock implements Lock {

    // 当前锁的拥有者
    volatile  AtomicReference<Thread> owner = new AtomicReference<>();
    // java q 线程安全
    volatile LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();


    /**
     * 获取锁
     */
    @Override
    public void lock() {
        boolean addQ = true;
        while (!tryLock()) {
            if (addQ) {
                // 塞到等待锁的集合中
                waiters.offer(Thread.currentThread());
                addQ = false;
            } else {
                // 挂起这个线程
                LockSupport.park();
                // 后续，等待其他线程释放锁，收到通知之后继续循环
            }
        }
        waiters.remove(Thread.currentThread());
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    /**
     * 尝试获取锁
     * @return
     */
    @Override
    public boolean tryLock() {
        return owner.compareAndSet(null, Thread.currentThread());
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        // cas 修改 owner 拥有者
        if (owner.compareAndSet(Thread.currentThread(), null)) {
            Iterator<Thread> iterator = waiters.iterator();
            while (iterator.hasNext()) {
                Thread next = iterator.next();
                LockSupport.unpark(next); // 唤醒线程继续 抢锁
            }
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
