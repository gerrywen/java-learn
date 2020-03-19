package ch12_02;


/**
 * program: java-learn->Client
 * description:
 * author: gerry
 * created: 2020-03-19 11:07
 **/
public class Client {
    public static void main(String[] args) {
//        test();
        // 定义一个痴迷的玩家
        GamePlayer gamePlayer = new GamePlayer("张三");
        // 然后再定义一个代练者
        IGamePlayer proxy = gamePlayer.getProxy();
        // 开始打游戏，记下时间戳
        System.out.println("开始时间是：2009-8-25 10:45");
        proxy.login("zhangSan","password");
        // 开始杀怪
        proxy.killBoss();
        // 升级
        proxy.upgrade();
        // 记录结束游戏时间
        System.out.println("结束时间是：2009-8-26 03:40");
    }

    private static void test() {
        // 定义一个痴迷的玩家
        GamePlayer gamePlayer = new GamePlayer("张三");
        // 开始打游戏，记下时间戳
        System.out.println("开始时间是：2009-8-25 10:45");
        gamePlayer.login("zhangSan","password");
        // 开始杀怪
        gamePlayer.killBoss();
        // 升级
        gamePlayer.upgrade();
        // 记录结束游戏时间
        System.out.println("结束时间是：2009-8-26 03:40");
    }

    private static void test1() {
        // 定义一个痴迷的玩家
        GamePlayer gamePlayer = new GamePlayer("张三");
        // 然后再定义一个代练者
        GamePlayerProxy proxy = new GamePlayerProxy(gamePlayer);
        // 开始打游戏，记下时间戳
        System.out.println("开始时间是：2009-8-25 10:45");
        proxy.login("zhangSan","password");
        // 开始杀怪
        proxy.killBoss();
        // 升级
        proxy.upgrade();
        // 记录结束游戏时间
        System.out.println("结束时间是：2009-8-26 03:40");
    }
}
