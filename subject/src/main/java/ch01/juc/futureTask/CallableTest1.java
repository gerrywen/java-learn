package ch01.juc.futureTask;

import java.util.concurrent.*;

/**
 * @author wenguoli
 * @description:
 * @date 2020/2/23 0023 21:24
 */
public class CallableTest1 {
    public static void main(String[] args) {
        //第一种方式
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();

        // FutureTask在高并发环境下确保任务只执行一次
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        executor.submit(futureTask);
        executor.shutdown();

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            if (futureTask.get() != null) {
                System.out.println("task运行结果" + futureTask.get());
            } else {
                System.out.println("未获取到结果");
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");

    }


    /**
     * 提交任务
     */
    public static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程在进行计算");
            Thread.sleep(1000L);
            int sum = 0;
            for (int i = 0; i < 1000; ++i) {
                sum += i;
            }
            return sum;
        }
    }
}
