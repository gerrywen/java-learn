package ch10_02;

/**
 * program: java-learn->HummerH1Model
 * description:
 * author: gerry
 * created: 2020-03-20 22:56
 **/
public class HummerH1Model extends HummerModel {
    private boolean alarmFlag = true; //要响喇叭

    @Override
    protected void start() {
        System.out.println("悍马H1发动...");
    }

    @Override
    protected void stop() {
        System.out.println("悍马H1停车...");
    }

    @Override
    protected void alarm() {
        System.out.println("悍马H1鸣笛...");
    }

    @Override
    protected void engineBoom() {
        System.out.println("悍马H1引擎声音是这样的...");
    }

    protected boolean isAlarm() {
        return this.alarmFlag;
    }

    /**
     * 要不要响喇叭，是由客户来决定的
     *
     * @param isAlarm
     */
    public void setAlarm(boolean isAlarm) {
        this.alarmFlag = isAlarm;
    }
}
