package ch12_05;

import java.lang.reflect.Proxy;

/**
 * program: java-learn->SubjectDynamicProxy
 * description: 具体业务的动态代理
 * author: gerry
 * created: 2020-03-19 22:33
 **/
public class SubjectDynamicProxy extends DynamicProxy{
    public static <T> T newProxyInstance(Subject subject) {
        // 获得ClassLoader
        ClassLoader loader = subject.getClass().getClassLoader();
        // 获得接口数组
        Class<?>[] interfaces = subject.getClass().getInterfaces();
        // 获得handler
        MyInvocationHandler h = new MyInvocationHandler(subject);
        return (T) Proxy.newProxyInstance(loader, interfaces, h);
    }
}
