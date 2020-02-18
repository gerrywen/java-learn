package ch01.lock.aqs.jucdemo.lock;

import ch01.lock.aqs.NeteaseAqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/2/18 10:28
 */
// 自己实现(独享锁) - 常用的
public class TonyLock implements Lock {

    // 抽象工具类AQS
    NeteaseAqs aqs = new NeteaseAqs(){
        // 模板设计模式
        @Override
        public boolean tryAcquire() {
            // cas原理
            return owner.compareAndSet(null, Thread.currentThread());
        }

        @Override
        public boolean tryRelease() {
            return owner.compareAndSet(Thread.currentThread(), null);
        }
    };

    @Override
    public void lock() {
        aqs.acquire();
    }

    @Override
    public void unlock() {
        aqs.release();
    }

    @Override
    public boolean tryLock() {
        return aqs.tryAcquire();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
