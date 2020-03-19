package ch12_05;

/**
 * program: java-learn->BeforeAdvice
 * description:
 * author: gerry
 * created: 2020-03-19 22:27
 **/
public class BeforeAdvice implements IAdvice {
    @Override
    public void exec() {
        System.out.println("我是前置通知，我被执行了！");
    }
}
