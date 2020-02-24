package ch01.juc.source.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author wenguoli
 * @description:
 * @date 2020/2/23 0023 21:50
 */
public interface Future <V> {
    /**
     * 尝试取消此次任务  mayInterruptIfRunning
     * true如果执行该任务的线程应该被中断;
     * 否则，正在进行的任务被允许完成
     */
    boolean cancel(boolean mayInterruptIfRunning);

    /**
     * 如果此任务在正常完成之前被取消，则返回 true 。
     */
    boolean isCancelled();

    /**
     * 返回true如果任务已完成。
     * 完成可能是由于正常终止，异常或取消
     * 在所有这些情况下，此方法将返回true 。
     */
    boolean isDone();

    /**
     * 等待计算完成然后返回结果
     */
    V get() throws InterruptedException, ExecutionException;

    /**
     * 在指定的时间之内进行等待，超时不等待
     */
    V get(long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException;
}
