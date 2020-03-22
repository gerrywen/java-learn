/*
 * Copyright (c) 2005, 2010, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package source.java.io;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

/**
 * This class holds a set of filenames to be deleted on VM exit through a shutdown hook.
 * A set is used both to prevent double-insertion of the same file as well as offer
 * quick removal.
 *
 * 这个类包含一组要在VM出口通过关闭钩子删除的文件名。
 * 一个集合既可以用来防止同一个文件的重复插入，也可以用来提供快速删除。
 *
 * DeleteOnExitHook主要用于当File对象调用deleteOnExit方法时，
 * 会在DeleteOnExitHook的LinkedHashMap存放相关的文件信息，用于在JVM正常退出的时候进行文件的清理
 *
 */

class DeleteOnExitHook {
    private static LinkedHashSet<String> files = new LinkedHashSet<>();
    static {
        // DeleteOnExitHook must be the last shutdown hook to be invoked.
        // Application shutdown hooks may add the first file to the
        // delete on exit list and cause the DeleteOnExitHook to be
        // registered during shutdown in progress. So set the
        // registerShutdownInProgress parameter to true.
        /**
         * DeleteOnExitHook必须是最后一个要调用的关闭钩子。
         * 应用程序关闭钩子可以将第一个文件添加到退出列表中的delete中，并在关闭过程中注册DeleteOnExitHook。
         * 因此将registerShutdownInProgress参数设置为true。
         */
        sun.misc.SharedSecrets.getJavaLangAccess()
            .registerShutdownHook(2 /* Shutdown hook invocation order */,
                true /* register even if shutdown in progress */,
                new Runnable() {
                    public void run() {
                       runHooks();
                    }
                }
        );
    }

    // 防止实例化
    private DeleteOnExitHook() {}

    /**
     * 线程同步添加文件
     * @param file
     */
    static synchronized void add(String file) {
        if(files == null) {
            // DeleteOnExitHook is running. Too late to add a file
            throw new IllegalStateException("Shutdown in progress");
        }

        files.add(file);
    }

    /**
     * 钩子函数运行
     */
    static void runHooks() {
        LinkedHashSet<String> theFiles;

        // 同步类锁
        synchronized (DeleteOnExitHook.class) {
            theFiles = files;
            files = null;
        }

        ArrayList<String> toBeDeleted = new ArrayList<>(theFiles);

        // reverse the list to maintain previous jdk deletion order.
        // Last in first deleted.
        Collections.reverse(toBeDeleted);
        for (String filename : toBeDeleted) {
            (new File(filename)).delete();
        }
    }
}
