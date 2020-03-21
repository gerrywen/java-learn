package ch18;

/**
 * program: java-learn->CashFactory
 * description:
 * author: gerry
 * created: 2020-03-16 21:04
 **/
public class CashFactory {

    public static CashSuper createCashAccept(String type) {
        CashSuper cs = null;
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
        return cs;
    }
}
