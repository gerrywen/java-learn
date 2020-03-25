package ch22;

/**
 * program: java-learn->Client
 * description:
 * author: gerry
 * created: 2020-03-25 21:32
 **/
public class Client {
    public static void main(String[] args) throws InterruptedException {
        // 定义出韩非子和李斯
        LiSi liSi = new LiSi();
        HanFeiZi hanFeiZi = new HanFeiZi();
        hanFeiZi.setHavingBreakfast(true);
        hanFeiZi.setHavingFun(true);
        // 观察早餐
        Spy breakfast = new Spy(hanFeiZi, liSi, "breakfast");
        //开始启动线程，监控
        breakfast.start();
        // 观察娱乐情况
        Spy fun = new Spy(hanFeiZi, liSi, "fun");
        fun.start();
        //然后我们看看韩非子在干什么
        Thread.sleep(1000); //主线程等待1秒后后再往下执行
        hanFeiZi.haveBreakfast();
        //韩非子娱乐了
        Thread.sleep(1000);
        hanFeiZi.haveFun();
    }
}
