package ch12_05;

import java.lang.reflect.Proxy;

/**
 * program: java-learn->Client
 * description:
 * author: gerry
 * created: 2020-03-19 22:27
 **/
public class Client {
    public static void main(String[] args) {
        // 定义一个主题
        Subject realSubject = new RealSubject();
        // 定义主题的代理
        Subject proxy = (Subject) SubjectDynamicProxy.newProxyInstance(realSubject);
        // 代理的行为
        proxy.doSomething("晚上十一点一起吃烧烤");
    }


    private static void test() {
        // 定义一个主题
        Subject realSubject = new RealSubject();
        // 定义一个Handler
        MyInvocationHandler handler = new MyInvocationHandler(realSubject);
        // 定义主题的代理
        Subject proxy = (Subject)DynamicProxy.newProxyInstance(realSubject.getClass().getClassLoader(),
                realSubject.getClass().getInterfaces(), handler);
        // 代理的行为
        proxy.doSomething("吃烧烤");
    }
}
