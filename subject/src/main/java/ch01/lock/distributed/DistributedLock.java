package ch01.lock.distributed;

/**
 * @author wenguoli
 * @description:
 * @date 2020/2/15 0015 13:53
 */
public interface DistributedLock {
    /**
     * 获取锁
     * @author zhi.li
     * @return 锁标识
     */
    String acquire();

    /**
     * 释放锁
     * @author zhi.li
     * @param indentifier
     * @return
     */
    boolean release(String indentifier);

}
