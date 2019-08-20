package b.observer.mode;

import b.observer.observer.Observer;

/**
 * 具体观察者-当前条件
 */
public class CurrentConditions implements Observer {
    private float mTemperatrue;
    private float mPressure;
    private float mHumidity;


    @Override
    public void update(float mTemperatrue, float mPressure, float mHumidity) {
        this.mHumidity = mHumidity;
        this.mPressure = mPressure;
        this.mTemperatrue = mTemperatrue;
        display();
    }

    public void display() {
        System.out.println("***Today mTemperatrue:" + mTemperatrue + "***");
        System.out.println("***Today mPressure:" + mPressure + "***");
        System.out.println("***Today mHumidity:" + mHumidity + "***");

    }
}
