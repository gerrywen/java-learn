package ch02;

import utils.TimeUtils;

public class Main {
    public static void main(String[] args) {
        TimeUtils.test("test1", new TimeUtils.Task() {
            public void execute() {
                test1();
            }
        });
    }

    public static void test1() {
        List<Object> list = new ArrayList<>();

        list.add(10);
        list.add(22);
        list.add(1, 666);

        System.out.println(list);
    }
}
