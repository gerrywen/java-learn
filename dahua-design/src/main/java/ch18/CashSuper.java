package ch18;

/**
 * program: java-learn->CashSuper
 * description: 现金收费抽象类
 * author: gerry
 * created: 2020-03-16 20:53
 **/
abstract public class CashSuper {
    /**
     * 收取现金超类抽象方法
     * @param money 收取现金
     * @return 返回当前价
     */
    public abstract double acceptCash(double money);
}
