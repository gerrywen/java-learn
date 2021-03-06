package ch01.lock.lock;

/**
 * @author wenguoli
 * @description:
 * @date 2020/2/14 0014 12:38
 */
public class LockDemo2 {
    int i = 0;

    public void add() {
        synchronized (this) {
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo2 ld = new LockDemo2();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    ld.add();
                }
            }).start();
        }
        Thread.sleep(2000L);
        System.out.println(ld.i);
    }
}
