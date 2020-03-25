package ch22;

/**
 * program: java-learn->Spy
 * description: 间谍
 * author: gerry
 * created: 2020-03-25 21:21
 **/
public class Spy extends Thread {
    private HanFeiZi hanFeiZi;
    private LiSi liSi;
    private String type;

    /**
     * 通过构造函数传递参数，我要监控的是谁，谁来监控，要监控什么
     *
     * @param _hanFeiZi
     * @param _liSi
     * @param _type
     */
    public Spy(HanFeiZi _hanFeiZi, LiSi _liSi, String _type) {
        this.hanFeiZi = _hanFeiZi;
        this.liSi = _liSi;
        this.type = _type;
    }

    @Override
    public void run() {
        while (true) {
            //监控是否在吃早餐
            if (this.type.equals("breakfast")) {
                //如果发现韩非子在吃饭，就通知李斯
                if (this.hanFeiZi.isHavingBreakfast()) {
                    this.liSi.update("韩非子在吃饭");
                    //重置状态，继续监控
                    this.hanFeiZi.setHavingBreakfast(false);
                }
            } else {//监控是否在娱乐
                if (this.hanFeiZi.isHavingFun()) {
                    this.liSi.update("韩非子在娱乐");
                    this.hanFeiZi.setHavingFun(false);
                }
            }
        }
    }
}
