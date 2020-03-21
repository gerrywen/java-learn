package ch18;

/**
 * program: java-learn->CashReturn
 * description: 返利收取子类
 * author: gerry
 * created: 2020-03-16 21:00
 **/
public class CashReturn extends CashSuper{
    private double moneyCondition = 0.0d;
    private double moneyReturn = 0.0d;

    public CashReturn(double moneyCondition, double moneyReturn) {
        this.moneyCondition = moneyCondition;
        this.moneyReturn = moneyReturn;
    }

    @Override
    public double acceptCash(double money) {
        double result = money;
        if (money >= moneyCondition) {
            result = money - Math.floor(money / moneyCondition) * moneyReturn;
        }
        return result;
    }
}
