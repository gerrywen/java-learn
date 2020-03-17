package ch15_01;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/3/17 10:27
 */
public class Test {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        IFruitFactory ff = new NorthFruitFactory();
        IFruit apple = ff.getApple();
        apple.get();

        IFruit banana = ff.getBanana();
        banana.get();

        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        IFruitFactory bb = new SouthFruitFactory();
        IFruit apple2 = bb.getApple();
        apple2.get();

        IFruit banana2 = bb.getBanana();
        banana2.get();

        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        //比如要增加室内innerApple,InnerBanana
        IFruitFactory cc = new InnerFruitFactory();
        IFruit apple3 = cc.getApple();
        apple3.get();
        IFruit banana3 = cc.getBanana();
        banana3.get();
    }
}
