package ch22_01;

/**
 * program: java-learn->LiSi
 * description:
 * author: gerry
 * created: 2020-03-25 21:19
 **/
public class LiSi implements ILiSi {
    /**
     * 首先李斯是个观察者，一旦韩非子有活动，他就知道，他就要向老板汇报
     *
     * @param context
     */
    @Override
    public void update(String context) {
        System.out.println("李斯:观察到韩非子活动，开始向老板汇报了...");
        this.reportToQinShiHuang(context);
        System.out.println("李斯：汇报完毕...\n");
    }

    /**
     * 汇报给秦始皇
     *
     * @param reportContext
     */
    private void reportToQinShiHuang(String reportContext) {
        System.out.println("李斯：报告，秦老板！韩非子有活动了--->" + reportContext);
    }
}
