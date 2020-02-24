package ch01.juc.source.forkjoin;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/2/24 17:26
 */

/**
 * 将生产异步的任务和消费完成的任务进行解耦。生产者提交任务执行。消费者消费完成的任务，
 * 并且按照任务完成的顺序去处理结果。CompletionService可以用于管理异步IO。
 * 执行读取的任务是在程序或系统的一部分提交的，当读取完成后，在系统不同的地方处理，很可能
 * 处理的顺序也不是他们提交上来的时候的顺序
 *
 *  典型的，CompletionService依赖于一个独立的Executor来执行任务，这样CompletionService就只需
 *  管理内部的任务完成的队列。ExecutorCompletionService就实现了CompletionService
 * @param <V>
 */
public interface CompletionService<V> {
    /**
     * Submits a value-returning task for execution and returns a Future
     * representing the pending results of the task.  Upon completion,
     * this task may be taken or polled.
     *
     * @param task the task to submit
     * @return a Future representing pending completion of the task
     * @throws RejectedExecutionException if the task cannot be
     *         scheduled for execution
     * @throws NullPointerException if the task is null
     */
    // 提交一个带返回值的任务，返回一个Future对象代表预期的任务执行结果。
    // 当执行结束后，这个任务执行结果会被take/poll取得
    Future<V> submit(Callable<V> task);

    /**
     * Submits a Runnable task for execution and returns a Future
     * representing that task.  Upon completion, this task may be
     * taken or polled.
     *
     * @param task the task to submit
     * @param result the result to return upon successful completion
     * @return a Future representing pending completion of the task,
     *         and whose {@code get()} method will return the given
     *         result value upon completion
     * @throws RejectedExecutionException if the task cannot be
     *         scheduled for execution
     * @throws NullPointerException if the task is null
     */
    // 提交一个Runnable任务去执行，返回一个Future对象代表此任务。
    // 当执行结束后，这个任务执行结果会被take/poll取得
    Future<V> submit(Runnable task, V result);

    /**
     * Retrieves and removes the Future representing the next
     * completed task, waiting if none are yet present.
     *
     * @return the Future representing the next completed task
     * @throws InterruptedException if interrupted while waiting
     */
    // 遍历并删除下一个完成的任务的Future对象，如果没有完成的任务则等待
    // 队列为空则阻塞
    Future<V> take() throws InterruptedException;

    /**
     * Retrieves and removes the Future representing the next
     * completed task, or {@code null} if none are present.
     *
     * @return the Future representing the next completed task, or
     *         {@code null} if none are present
     */
    // 遍历并删除下一个完成的任务的Future对象，如果没有完成的任务则等待
    // 队列为空则返回null
    Future<V> poll();

    /**
     * Retrieves and removes the Future representing the next
     * completed task, waiting if necessary up to the specified wait
     * time if none are yet present.
     *
     * @param timeout how long to wait before giving up, in units of
     *        {@code unit}
     * @param unit a {@code TimeUnit} determining how to interpret the
     *        {@code timeout} parameter
     * @return the Future representing the next completed task or
     *         {@code null} if the specified waiting time elapses
     *         before one is present
     * @throws InterruptedException if interrupted while waiting
     */
    // 遍历并删除下一个完成的任务的Future对象，如果没有完成的任务则等待指定的时间
    Future<V> poll(long timeout, TimeUnit unit) throws InterruptedException;
}

