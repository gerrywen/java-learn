package ch01.lock.readWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wenguoli
 * @description: 读写锁（既保证了读数据的效率，也保证数据的一致性）
 * @date 2020/2/15 0015 15:21
 */
public class ReentrantReadWriteLockDemo2 {
    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        final ReentrantReadWriteLockDemo2 readWriteLockDemo2 = new ReentrantReadWriteLockDemo2();
        // 多线程同时读/写
        new Thread(() -> {
            readWriteLockDemo2.read(Thread.currentThread());
        }).start();

        new Thread(() -> {
            readWriteLockDemo2.read(Thread.currentThread());
        }).start();

        new Thread(() -> {
            readWriteLockDemo2.write(Thread.currentThread());
        }).start();

    }

    // 多线程读，共享锁
    public void read(Thread thread) {
        readWriteLock.readLock().lock(); // 读锁
        try {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName() + "正在进行“读”操作");
            }
            System.out.println(thread.getName() + "“读”操作完毕");
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * 写
     */
    public void write(Thread thread) {
        readWriteLock.writeLock().lock(); // 写锁
        try {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName() + "正在进行“写”操作");
            }
            System.out.println(thread.getName() + "“写”操作完毕");
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
