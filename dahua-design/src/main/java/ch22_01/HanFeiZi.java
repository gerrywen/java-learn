package ch22_01;

/**
 * program: java-learn->HanFeiZi
 * description: 具体的被观察者
 * author: gerry
 * created: 2020-03-25 21:15
 **/
public class HanFeiZi implements IHanFeiZi {

    // 把李斯声明出来
    private ILiSi liSi = new LiSi();

    /**
     * 韩非子要吃饭了
     */
    @Override
    public void haveBreakfast() {
        System.out.println("韩非子:开始吃饭了...");
        // 通知李斯
        this.liSi.update("韩非子在吃饭");
    }

    /**
     * 韩非子开始娱乐了
     */
    @Override
    public void haveFun() {
        System.out.println("韩非子:开始娱乐了...");
        this.liSi.update("韩非子在娱乐");
    }

}
