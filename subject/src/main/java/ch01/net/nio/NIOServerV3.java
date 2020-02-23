package ch01.net.nio;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wenguoli
 * @description:  NIO selector 多路复用reactor线程模型
 * @date 2020/2/23 0023 18:49
 */
public class NIOServerV3 {
    /** 处理业务操作的线程 */
    private static ExecutorService workPool = Executors.newCachedThreadPool();

    

}
