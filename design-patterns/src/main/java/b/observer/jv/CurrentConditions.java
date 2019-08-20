package b.observer.jv;

import java.util.Observable;
import java.util.Observer;
import b.observer.jv.WeatherData.Data;

//具体观察者类：当前条件
public class CurrentConditions implements Observer {
    private float mTemperatrue;
    private float mPressure;
    private float mHumidity;

    @Override
    public void update(Observable arg0, Object arg1) {
        this.mTemperatrue=((Data)(arg1)).mTemperatrue;
        this.mPressure=((Data)(arg1)).mPressure;
        this.mHumidity=((Data)(arg1)).mHumidity;
        display();
    }

    public void display()
    {
        System.out.println("***Today mTemperatrue:" +mTemperatrue+"***");
        System.out.println("***Today mPressure:" +mPressure+"***");
        System.out.println("***Today mHumidity:" +mHumidity+"***");
    }
}
