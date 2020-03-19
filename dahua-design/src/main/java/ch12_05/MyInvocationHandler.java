package ch12_05;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * program: java-learn->MyInvocationHandler
 * description:
 * author: gerry
 * created: 2020-03-19 22:19
 **/
public class MyInvocationHandler implements InvocationHandler {

    // 被代理的对象
    private Object target = null;

    // 通过构造函数传递一个对象
    public MyInvocationHandler(Object _obj) {
        this.target = _obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 执行被代理的方法
        return method.invoke(this.target, args);
    }
}
