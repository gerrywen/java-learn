package ch01.lock.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wenguoli
 * @description: 演示可重入
 * @date 2020/2/15 0015 14:43
 */
public class ReentrantDemo1 {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            System.out.println("第一次获取锁");
            System.out.println("当前线程获取锁的次数" + lock.getHoldCount());
            lock.lock();
            System.out.println("第二次获取锁了");
            System.out.println("当前线程获取锁的次数" + lock.getHoldCount());
        }finally {
            lock.unlock();
            lock.unlock();
        }
        System.out.println("当前线程获取锁的次数" + lock.getHoldCount());

        // 如果不释放，此时其他线程是拿不到锁的
        new Thread(() -> {
            System.out.println(Thread.currentThread() + " 期望抢到锁");
            lock.lock();
            System.out.println(Thread.currentThread() + " 线程拿到了锁");
        }).start();

    }
}
