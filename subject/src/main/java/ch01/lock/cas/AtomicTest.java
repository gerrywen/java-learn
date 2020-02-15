package ch01.lock.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wenguoli
 * @description: atomic 相关测试代码
 * @date 2020/2/15 0015 14:24
 */
public class AtomicTest {
    public static void main(String[] args) throws InterruptedException {
        // 自增
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    atomicInteger.incrementAndGet();
                }
            }).start();
        }
        Thread.sleep(2000L);
        System.out.println(atomicInteger.get());
    }
}
