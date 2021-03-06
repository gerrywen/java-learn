package ch22_02;

/**
 * program: java-learn->WangSi
 * description:
 * author: gerry
 * created: 2020-03-25 22:15
 **/
public class WangSi implements Observer {
    /**
     * 王斯，看到韩非子有活动
     *
     * @param context
     */
    @Override
    public void update(String context) {
        System.out.println("王斯：观察到韩非子活动，自己也开始活动了...");
        this.cry(context);
        System.out.println("王斯：哭死了...\n");
    }

    private void cry(String context) {
        System.out.println("王斯：因为" + context + "，--所以我悲伤呀！");
    }
}
