package ch01.lock.distributed;

import redis.clients.jedis.Jedis;

/**
 * @author wenguoli
 * @description:
 * @date 2020/2/15 0015 15:51
 */
public class RedisDistributedLockTest {
    static int n = 500;
    public static void secskill() {
        System.out.println(--n);
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                RedisDistributedLock lock = null;
                String unLockIdentify = null;
                try {
                    Jedis conn = new Jedis("47.98.184.122",63791);
                    lock = new RedisDistributedLock(conn, "test1");
                    unLockIdentify = lock.acquire();
                    System.out.println(Thread.currentThread().getName() + "正在运行");
                }finally {
                    if (lock != null) {
                        lock.release(unLockIdentify);
                    }
                }
            }
        };

        // 启动10个线程
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }
    }
}
