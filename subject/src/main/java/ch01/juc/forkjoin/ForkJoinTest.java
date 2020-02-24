package ch01.juc.forkjoin;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/2/24 15:51
 */
/**
 * Java在JDK7之后加入了并行计算的框架Fork/Join，可以解决我们系统中大数据计算的性能问题。
 * Fork/Join采用的是分治法，Fork是将一个大任务拆分成若干个子任务，子任务分别去计算，而Join是获取到子任务的计算结果，然后合并，这个是递归的过程。
 * 子任务被分配到不同的核上执行时，效率最高。
 */
public class ForkJoinTest extends RecursiveTask<Long> {

    private static final int THREADSHOLD = 50000; // 阀门
    private long[] array;   // 需要计算的数组
    private int low;        // 最小
    private int hight;      // 最大

    public ForkJoinTest(long[] array, int low, int hight) {
        this.array = array;
        this.low = low;
        this.hight = hight;
    }


    @Override
    protected Long compute() {
        long sum = 0;
        if (hight - low < THREADSHOLD) {
            for(int i = low; i < hight; ++i) {
                sum += array[i];
            }
        } else {
            int middle = (low + hight) >>> 1;
            ForkJoinTest left = new ForkJoinTest(array, low, middle);
            ForkJoinTest right = new ForkJoinTest(array, middle + 1, hight);
            left.fork();
            right.fork();
            sum = left.join() + right.join();
        }
        return sum;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long[] array = genArray(1000000);
        System.out.println(Arrays.toString(array));

        ForkJoinTest forkJoinTest = new ForkJoinTest(array, 0, array.length - 1);
        long begin = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(forkJoinTest);
        Long result = forkJoinTest.get();

        long end = System.currentTimeMillis();

        System.out.println(String.format("结果 %s 耗时 %sms", result, end - begin));
    }
    /**
     * 生成long数组
     * @param size
     * @return
     */
    private static long[] genArray(int size) {
        long[] array = new long[size];
        for (int i = 0; i < size; i++) {
            array[i] = new Random().nextLong();
        }
        return array;
    }
}
