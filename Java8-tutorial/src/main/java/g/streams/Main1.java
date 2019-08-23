package g.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Main1 {
    public static void main(String[] args) {
//        test1(); // sequential sort took: 1427 ms
        test2(); // parallel sort took: 1146 ms
    }

    private static List valueList() {
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        return values;
    }

    private static void test1() {
        long t0 = System.nanoTime();
        long count = valueList().stream().sorted().count();
        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));
    }


    private static void test2() {
        long t0 = System.nanoTime();
        long count = valueList().parallelStream().sorted().count();
        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));
    }



}
