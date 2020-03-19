package ch12_04;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * program: java-learn->GamePlayIH
 * description:
 * author: gerry
 * created: 2020-03-19 21:18
 **/
public class GamePlayIH implements InvocationHandler {

    // 被代理者
    Class cls = null;
    // 被代理的实例
    Object obj = null;

    // 构造方法
    public GamePlayIH(Object _obj) {
        this.obj = _obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在真实的对象执行之前我们可以添加自己的操作
        System.out.println("before invoke。。。");
        Object result = method.invoke(obj, args);
        //在真实的对象执行之后我们可以添加自己的操作
        System.out.println("after invoke。。。");
        // 如果是登录方法，则发送信息
        if (method.getName().equalsIgnoreCase("login")) {
            System.out.println("有人在用我的账号登录！");
        }
        return result;
    }
}
