package ch01.lock.distributed;

import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.UUID;

/**
 * @author wenguoli
 * @description: 刚刚介绍完了错误的姿势后，从上面错误姿势中，我们可以知道，要使用Redis实现分布式锁。加锁操作的正确姿势为：
 * <p>
 * 使用setnx命令保证互斥性
 * 需要设置锁的过期时间，避免死锁
 * setnx和设置过期时间需要保持原子性，避免在设置setnx成功之后在设置过期时间客户端崩溃导致死锁
 * 加锁的Value 值为一个唯一标示。可以采用UUID作为唯一标示。加锁成功后需要把唯一标示返回给客户端来用来客户端进行解锁操作
 * <p>
 * 解锁的正确姿势为：
 * 　1. 需要拿加锁成功的唯一标示要进行解锁，从而保证加锁和解锁的是同一个客户端
 * 　2. 解锁操作需要比较唯一标示是否相等，相等再执行删除操作。这2个操作可以采用Lua脚本方式使2个命令的原子性。
 * @date 2020/2/15 0015 13:54
 */
public class RedisDistributedLock implements DistributedLock {

    private static final String LOCK_SUCCESS = "OK";
    private static final Long RELEASE_SUCCESS = 1L;
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    /**
     * redis 客户端
     */
    private Jedis jedis;

    /**
     * 分布式锁的键值
     */
    private String lockKey;

    /**
     * 锁的超时时间 10s
     */
    int expireTime = 10 * 1000;

    /**
     * 锁等待，防止线程饥饿
     */
    int acquireTimeout = 1 * 1000;

    /**
     * 获取指定键值的锁
     * @param jedis jedis Redis客户端
     * @param lockKey 锁的键值
     */
    public RedisDistributedLock(Jedis jedis, String lockKey) {
        this.jedis = jedis;
        this.lockKey = lockKey;
    }

    /**
     * 获取指定键值的锁
     *
     * @param jedis   jedis Redis客户端
     * @param lockKey 锁的键值
     */
    public RedisDistributedLock(Jedis jedis, String lockKey, int acquireTimeout) {
        this.jedis = jedis;
        this.lockKey = lockKey;
        this.acquireTimeout = acquireTimeout;
    }

    /**
     * 获取指定键值的锁,同时设置获取锁超时时间和锁过期时间
     *
     * @param jedis          jedis Redis客户端
     * @param lockKey        锁的键值
     * @param acquireTimeout 获取锁超时时间
     * @param expireTime     锁失效时间
     */
    public RedisDistributedLock(Jedis jedis, String lockKey, int acquireTimeout, int expireTime) {
        this.jedis = jedis;
        this.lockKey = lockKey;
        this.acquireTimeout = acquireTimeout;
        this.expireTime = expireTime;
    }

    @Override
    public String acquire() {
        try {
            // 获取锁的超时时间，超过这个时间则放弃获取锁
            long end = System.currentTimeMillis() + acquireTimeout;
            // 随机生成一个value
            String requireToken = UUID.randomUUID().toString();
            while (System.currentTimeMillis() < end) {
                // 设置 redis NX
                String result = jedis.set(lockKey, requireToken, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
                // 判断是否设置保存成功
                if (LOCK_SUCCESS.equals(result)) return requireToken;
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    System.out.println("acquire set requireToken error" +  e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }

        } catch (Exception e) {
            System.out.println("acquire lock due to error:" + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean release(String identify) {
        if (identify == null) return false;
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = new Object();
        try {
            result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(identify));
            if (RELEASE_SUCCESS.equals(result)) {
                System.out.println("release lock success, requestToken:" + identify);
                return true;
            }
        } catch (Exception e) {
            System.out.println("release lock due to error:" + e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

        System.out.println("release lock failed, requestToken:" + identify + ", result:" + result);
        return false;
    }
}
