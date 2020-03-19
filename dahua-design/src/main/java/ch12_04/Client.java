package ch12_04;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * program: java-learn->Client
 * description:
 * author: gerry
 * created: 2020-03-19 21:26
 **/
public class Client {
    public static void main(String[] args) {
        IGamePlayer player = new GamePlayer("张三");
        // 代理对象的调用处理程序，我们将要代理的真实对象传入代理对象的调用处理的构造函数中，
        // 最终代理对象的调用处理程序会调用真实对象的方法
        InvocationHandler handler = new GamePlayIH(player);
        // 开始打游戏，记下时间戳
        System.out.println("开始时间是：2009-8-2510:45");
        // 加载类
        ClassLoader cl = player.getClass().getClassLoader();
        //动态产生一个代理者
        /**
         * 通过Proxy类的newProxyInstance方法创建代理对象，我们来看下方法中的参数
         * 第一个参数：player.getClass().getClassLoader()，使用handler对象的classloader对象来加载我们的代理对象
         * 第二个参数：player.getClass().getInterfaces()，这里为代理类提供的接口是真实对象实现的接口，这样代理对象就能像真实对象一样调用接口中的所有方法
         * 第三个参数：handler，我们将代理对象关联到上面的InvocationHandler对象上
         */
        IGamePlayer proxy = (IGamePlayer)Proxy.newProxyInstance(cl, player.getClass().getInterfaces(), handler);
        proxy.login("zhangSan","password");
        // 开始杀怪
        proxy.killBoss();
        // 升级
        proxy.upgrade();
        // 记录结束游戏时间
        System.out.println("结束时间是：2009-8-26 03:40");
    }
}
