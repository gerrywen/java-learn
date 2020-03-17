package ch06;

/**
 * program: java-learn->ConcreteDecoratorB
 * description:
 * author: gerry
 * created: 2020-03-17 21:04
 **/
public class ConcreteDecoratorB extends Decorator {
    @Override
    public void operation() {
        super.operation();
        addedBehavior();
        System.out.println("具体装饰对象B的操作");
    }

    private void addedBehavior() {
        System.out.println("添加装饰对象B的行为");
    }
}
