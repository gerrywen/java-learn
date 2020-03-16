package ch02;

/**
 * program: java-learn->CashContext
 * description: 策略模式现金上下文类
 * author: gerry
 * created: 2020-03-16 21:13
 **/
public class CashContext {
    private CashSuper cs;

    public CashContext(CashSuper cs) {
        this.cs = cs;
    }

    public double getResult(double money){
        return cs.acceptCash(money);
    }
}
