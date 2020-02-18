package ch01.lock.aqs.jucdemo.lock;

import java.util.concurrent.locks.Lock;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/2/18 10:17
 */
public class LockDemo3 {
    int i = 0;

    Lock lock = new TonyLock();

    public void add() {
        lock.lock();
        try {
            i++;// 三个步骤
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo3 ld = new LockDemo3();

        for (int i =0; i < 2 ; ++i) {
            new Thread(() -> {
                for (int j = 0; j < 10000; ++j){
                    ld.add();
                }
            }).start();
        }

        Thread.sleep(2000L);
        System.out.println(ld.i);
    }
}
