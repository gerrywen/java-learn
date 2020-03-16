package ch02;

/**
 * program: java-learn->CashContext
 * description: 策略模式+简单工厂类现金上下文类
 * author: gerry
 * created: 2020-03-16 21:13
 **/
public class CashContextFactory {
    private CashSuper cs;

    public CashContextFactory(String type) {
        switch (type) {
            case "正常收费":
                cs = new CashNormal();
                break;
            case "满300减100":
                cs = new CashReturn(300,100);
                break;
            case "打8折":
                cs = new CashRebate(0.8);
                break;
        }
    }

    public double getResult(double money){
        return cs.acceptCash(money);
    }
}
