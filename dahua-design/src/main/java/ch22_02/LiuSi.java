package ch22_02;

/**
 * program: java-learn->LiuSi
 * description:
 * author: gerry
 * created: 2020-03-25 22:16
 **/
public class LiuSi implements Observer {
    /**
     * 刘斯，观察到韩非子活动后，自己也得做一些事
     *
     * @param context
     */
    @Override
    public void update(String context) {
        System.out.println("刘斯：观察到韩非子活动，开始动作了...");
        this.happy(context);
        System.out.println("刘斯：乐死了\n");
    }

    private void happy(String context) {
        System.out.println("刘斯：因为" + context + ",--所以我快乐呀！");
    }
}
