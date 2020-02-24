package ch01.juc.forkjoin;

import java.util.Random;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/2/24 17:45
 */

/**
 * 现在有一组需要并发执行的任务，各任务执行周期不相同，客户端希望完成一个任务则立即取得执行结果。该场景下，使用ExecutorCompletionService即可优雅的实现。
 * 这里选用Runnable作为任务，对ExecutorCompletionService来说，Runnable和Callable都一样，Runnable最终也会封装为Callable
 */
public class CompletionServiceTest {

    private static ExecutorService executor;
    private static int numTask = 50;
    private static CompletionService<String> completionService;

    private static class MyTask implements Runnable {

        private String taskName;

        private MyTask(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            long s = System.currentTimeMillis();
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.taskName + " finished! The total time is " + (System.currentTimeMillis() - s) + "ms");
        }
    }


    public static void main(String[] args) {
        startAll();
        try {
            get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();//切记需要关闭线程池，否则，进程不会结束
    }

    private static void startAll() {
        // Executor用于执行任务
        if (null == executor) {
            executor = Executors.newFixedThreadPool(numTask);
        }
        // 来管理大量任务执行的结果
        if (null == completionService) {
            completionService = new ExecutorCompletionService<String>(executor);//使用默认队列
        }
        for (int i = 0; i < numTask; i++) {
            completionService.submit(new MyTask("Task" + i), "task" + i + " return");
        }
    }

    private static void get() throws InterruptedException, ExecutionException {
        if (null != completionService) {
            for (int i = 0; i < numTask; i++) {
                System.out.println(completionService.take().get());
            }
        }
    }
}
