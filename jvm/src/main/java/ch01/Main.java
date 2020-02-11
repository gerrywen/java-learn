package ch01;

import java.util.ArrayList;

/**
 * @author wenguoli
 * @description:
 * @date 2020/2/11 0011 21:17
 */
public class Main {
    public static void main(String[] args) {
        // -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=G:/java/jvm/ch01 -Xms20m -Xmx20m
        ArrayList<Demo> list = new ArrayList<>();
        while (true) {
            list.add(new Demo());
        }
    }

    // jconsole
}
