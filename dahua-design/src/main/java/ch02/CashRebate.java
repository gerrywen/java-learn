package ch02;

/**
 * program: java-learn->CashRebate
 * description: 打折收费子类
 * author: gerry
 * created: 2020-03-16 20:57
 **/
public class CashRebate extends CashSuper {
    private double moneyRebate = 1d;

    public CashRebate(double moneyRebate) {
        this.moneyRebate = moneyRebate;
    }

    @Override
    public double acceptCash(double money) {
        return money * moneyRebate;
    }
}
