package ch29;

/**
 * program: java-learn->Clothes
 * description: 服装
 * author: gerry
 * created: 2020-03-28 20:36
 **/
public class Clothes extends Product{
    @Override
    public void beProducted() {
        System.out.println("生产出的衣服是这样的...");
    }

    @Override
    public void beSelled() {
        System.out.println("生产出的衣服卖出去了...");
    }
}
