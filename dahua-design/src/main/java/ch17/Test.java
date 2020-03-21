package ch17;

/**
 * program: java-learn->Test
 * description:
 * author: gerry
 * created: 2020-03-17 21:06
 **/
public class Test {

    public static void main(String[] args) {

        test1();
    }

    private static void test1() {
        ConcreteComponent c = new ConcreteComponent();
        ConcreteDecoratorA d1 = new ConcreteDecoratorA();
        ConcreteDecoratorB d2 = new ConcreteDecoratorB();
        d1.setComponent(c);
        d2.setComponent(d1);
        d2.operation();

    }
}
