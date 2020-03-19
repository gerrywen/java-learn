package ch12_05;

/**
 * program: java-learn->RealSubject
 * description:
 * author: gerry
 * created: 2020-03-19 22:18
 **/
public class RealSubject implements Subject {
    @Override
    public void doSomething(String str) {
        System.out.println("do something!---->" + str);
    }
}
