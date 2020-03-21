package ch18;

/**
 * program: java-learn->Test
 * description:
 * author: gerry
 * created: 2020-03-16 21:08
 **/
public class Test {
    public static void main(String[] args) {
        CashContextFactory cashContextFactory = new CashContextFactory(CashEnum.CASH_REBATE.getDescription());
        double result = cashContextFactory.getResult(506.55);
        System.out.println(result);
    }

    private void cashContext() {
        CashContext cashContext = new CashContext(new CashNormal());
        double result = cashContext.getResult(30.45);
        System.out.println(result);


        CashContext cashContext1 = new CashContext(new CashReturn(40, 3));
        double result1 = cashContext1.getResult(40.45);
        System.out.println(result1);

        CashContext cashContext2 = new CashContext(new CashRebate(5.6));
        double result2 = cashContext2.getResult(30.45);
        System.out.println(result2);
    }

    private void cashFactory() {
        CashSuper cs = CashFactory.createCashAccept("满300减100");
        double rs = cs.acceptCash(400);
        System.out.println(rs);
    }
}
