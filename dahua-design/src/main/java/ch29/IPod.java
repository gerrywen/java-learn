package ch29;

/**
 * program: java-learn->IPod
 * description:
 * author: gerry
 * created: 2020-03-28 20:27
 **/
public class IPod extends Product {
    @Override
    public void beProducted() {
        System.out.println("生产出的iPod是这样的...");
    }

    @Override
    public void beSelled() {
        System.out.println("生产出的iPod卖出去了...");
    }
}
