package ch01.juc.source.forkjoin;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * description: 　
 *
 * java多线程之Executor 与 ExecutorService两个基本接口
 * https://www.cnblogs.com/heqiyoujing/p/11180579.html
 *
 * ExecutorService 是一个接口，提供了管理终止的方法，以及可为跟踪一个或多个异步任务执行状况而生成Future 的方法。
 *
 * 三个实现类：AbstractExecutorService（默认实现类） , ScheduledThreadPoolExecutor, ThreadPoolExecutor
 * @author wenguoli
 * @date 2020/2/24 16:05
 */
public interface ExecutorService extends Executor {
    /**
     * 启动一次顺序关闭，执行以前提交的任务，但不接受新任务。如果已经关闭，则调用没有其他作用。
     */
    void shutdown();

    /**
     * 试图停止所有正在执行的活动任务，暂停处理正在等待的任务，并返回等待执行的任务列表。
     * 无法保证能够停止正在处理的活动执行任务，但是会尽力尝试。
     * 例如，在 ThreadPoolExecutor 中，通过 Thread.interrupt() 来取消典型的实现，所以如果任务无法响应中断，则永远无法终止。
     * @return
     */
    List<Runnable> shutdownNow();

    /**
     * 如果此执行程序已关闭，则返回 true。
     */
    boolean isShutdown();

    /**
     * 如果关闭后所有任务都已完成，则返回 true。
     * 注意，除非首先调用 shutdown 或 shutdownNow，否则 isTerminated 永不为 true。
     */
    boolean isTerminated();

    /**
     * 一直等待，直到所有任务完成。请求关闭、发生超时或者当前线程中断，无论哪一个首先发生之后，都将导致阻塞，直到所有任务完成执行，
     * 或者超时时间的到来如果此执行程序终止，则返回 true；如果终止前超时期满，则返回 false
     */
    boolean awaitTermination(long timeout, TimeUnit unit)
            throws InterruptedException;

    /**
     * 提交一个返回值的任务用于执行，返回一个表示任务的未决结果的 Future。该 Future 的 get 方法在成功完成时将会返回该任务的结果。
     * 如果想立即阻塞任务的等待，则可以使用 result = exec.submit(aCallable).get(); 形式的构造
     */
    <T> Future<T> submit(Callable<T> task);

    /**
     * 提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future。
     * 该 Future 的 get 方法在成功完成时将会返回给定的结果。
     */
    <T> Future<T> submit(Runnable task, T result);

    /**
     * 提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future。
     * 该 Future 的 get 方法在成功 完成时将会返回 null。
     */
    Future<?> submit(Runnable task);

    /**
     * 执行给定的任务，当所有任务完成时，返回保持任务状态和结果的 Future 列表。返回列表的所有元素的 Future.isDone() 为 true。
     * 注意，可以正常地或通过抛出异常来终止已完成 任务。如果正在进行此操作时修改了给定的 collection，则此方法的结果是不确定的。
     */
    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
            throws InterruptedException;

    /**
     * 超时等待，同上。
     */
    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,
                                  long timeout, TimeUnit unit)
            throws InterruptedException;

    /**
     * 与 invokeAll的区别是，任务列表里只要有一个任务完成了，就立即返回。而且一旦正常或异常返回后，则取消尚未完成的任务。
     */
    <T> T invokeAny(Collection<? extends Callable<T>> tasks)
            throws InterruptedException, ExecutionException;

    /**
     * 超时等待，同上。
     */
    <T> T invokeAny(Collection<? extends Callable<T>> tasks,
                    long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException;
}
