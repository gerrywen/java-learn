package ch01.juc.source.future;

import java.util.concurrent.Future;

/**
 * @author wenguoli
 * @description:
 * @date 2020/2/23 0023 21:54
 */
public interface RunnableFuture<V> extends Runnable, Future<V> {

    void run();
}
