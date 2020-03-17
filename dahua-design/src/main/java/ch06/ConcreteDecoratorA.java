package ch06;

/**
 * program: java-learn->ConcreteDecoratorA
 * description:
 * author: gerry
 * created: 2020-03-17 20:53
 **/
public class ConcreteDecoratorA extends Decorator {

    private String addedState;

    @Override
    public void operation() {
        super.operation();
        addedState = "new state";
        System.out.println("具体装饰对象A的操作:" + addedState);
    }
}
