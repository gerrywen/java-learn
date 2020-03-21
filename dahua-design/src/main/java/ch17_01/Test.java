package ch17_01;

/**
 * program: java-learn->Test
 * description:
 * author: gerry
 * created: 2020-03-17 21:35
 **/
public class Test {
    public static void main(String[] args) {
        Person xc = new Person("小菜");
        System.out.println("第一种装扮：");
        PoQiuXie poQiuXie = new PoQiuXie();
        LingDai lingDai = new LingDai();
        TShirts tShirts = new TShirts();
        poQiuXie.Decorate(xc);
        lingDai.Decorate(poQiuXie);
        tShirts.Decorate(lingDai);
        tShirts.show();

        System.out.println("\n");

        System.out.println("第二种装扮：");
        PiXie piXie = new PiXie();
        XiFu xiFu = new XiFu();
        BigTrouser bigTrouser = new BigTrouser();
        piXie.Decorate(xc);
        xiFu.Decorate(piXie);
        bigTrouser.Decorate(xiFu);
        bigTrouser.show();
    }
}
