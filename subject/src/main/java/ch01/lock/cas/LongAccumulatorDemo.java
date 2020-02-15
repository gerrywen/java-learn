package ch01.lock.cas;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;

/**
 * @author wenguoli
 * @description: LongAdder增强版，处理累加之外，可以自行定义其他计算
 * @date 2020/2/15 0015 14:36
 */
public class LongAccumulatorDemo {
    public static void main(String[] args) throws InterruptedException{
        LongAccumulator accumulator = new LongAccumulator(new LongBinaryOperator() {
            @Override
            public long applyAsLong(long left, long right) {
                // 返回最大值，这就是自定义的计算
                return Math.min(left, right);
            }
        }, 0);

        // 1000个线程
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            new Thread(() -> {
                accumulator.accumulate(finalI); // 此处实际就是执行上面定义的操作
            }).start();
        }

        Thread.sleep(2000L);
        System.out.println(accumulator.longValue()); // 打印出结果
    }
}
