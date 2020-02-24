package ch01.juc.source.forkjoin;

/**
 * description: 执行已提交的 Runnable 任务的对象
 * 此接口提供一种将任务提交与每个任务将如何运行的机制（包括线程使用的细节、调度等）分离开来的方法。
 * 通常使用Executor 而不是显式地创建线程
 * @author wenguoli
 * @date 2020/2/24 16:03
 */
public interface Executor {

    /**
     * 在未来某个时间执行给定的命令。该命令可能在新的线程、已入池的线程或者正调用的线程中执行，这由 Executor 实现决定。
     *
     * 参数：command - 可运行的任务
     * 抛出：RejectedExecutionException - 如果不能接受执行此任务。
     * NullPointerException - 如果命令为 null
     *
     * @param command
     */
    void execute(Runnable command);
}
