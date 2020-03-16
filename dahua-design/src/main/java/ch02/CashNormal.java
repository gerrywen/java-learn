package ch02;

/**
 * program: java-learn->CashNormal
 * description: 正常收取子类
 * author: gerry
 * created: 2020-03-16 20:55
 **/
public class CashNormal extends CashSuper {
    @Override
    public double acceptCash(double money) {
        return money;
    }
}
