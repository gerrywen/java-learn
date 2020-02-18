package ch01.lock.aqs;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/2/18 9:42
 */
public class AQSdemo {
    // 同步资源状态
    volatile AtomicInteger state = new AtomicInteger(0);
    // 当前锁的拥有者
    protected volatile AtomicReference<Thread> owner = new AtomicReference<>();
    // java q 线程安全
    public volatile LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();


    /**
     * 获取锁
     */
    public void acquire() {
        boolean addQ = true;
        while (!tryAcquire()) {
            if (addQ) {
                // 塞到等待锁的集合中
                waiters.offer(Thread.currentThread());
                addQ = false;
            } else {
                // 挂起这个线程
                LockSupport.park();
                // 后续，等待其他线程释放锁，收到通知之后继续循环
            }
        }
        waiters.remove(Thread.currentThread());
    }

    /**
     * 释放锁
     */
    public void release() {
        // cas 修改 owner 拥有者
        if (tryRelease()) {
            Iterator<Thread> iterator = waiters.iterator();
            while (iterator.hasNext()) {
                Thread next = iterator.next();
                LockSupport.unpark(next);  // 唤醒线程继续 抢锁
            }
        }
    }
    // 判断量够不够
    public void acquireShared() {
        boolean addQ = true;
        while (tryAcquireShared() < 0) {
            if (addQ) {
                // 塞到等待锁的集合中
                waiters.offer(Thread.currentThread());
                addQ = false;
            } else {
                // 挂起这个线程
                LockSupport.park();
                // 后续，等待其他线程释放锁，收到通知之后继续循环
            }
        }
        waiters.remove(Thread.currentThread());
    }

    public void releaseShared() {
        // cas 修改 owner 拥有者
        if (tryReleaseShared()) {
            Iterator<Thread> iterator = waiters.iterator();
            while (iterator.hasNext()) {
                Thread next = iterator.next();
                LockSupport.unpark(next);  // 唤醒线程继续 抢锁
            }
        }
    }

    /**
     * 获取锁
     * @return
     */
    public boolean tryAcquire() {
        throw new UnsupportedOperationException();
    }

    /**
     * 释放锁
     * @return
     */
    public boolean tryRelease() {
        throw new UnsupportedOperationException();
    }

    /**
     * 共享锁
     * @return
     */
    public int tryAcquireShared() {
        throw new UnsupportedOperationException();
    }

    /**
     * 共享释放锁
     * @return
     */
    public boolean tryReleaseShared() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取 同步资源状态
     * @return
     */
    public AtomicInteger getState() {
        return state;
    }

    /**
     * 设置 同步资源状态
     * @param state
     */
    public void setState(AtomicInteger state) {
        this.state = state;
    }
}
