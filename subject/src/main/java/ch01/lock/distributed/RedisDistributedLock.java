package ch01.lock.distributed;

/**
 * @author wenguoli
 * @description:
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
//    private Jedis jedis;

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
    int acquireTimeout  = 1 * 1000;



    @Override
    public String acquire() {
        return null;
    }

    @Override
    public boolean release(String indentifier) {
        return false;
    }
}
