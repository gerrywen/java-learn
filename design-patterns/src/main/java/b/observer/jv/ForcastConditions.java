package b.observer.jv;

import java.util.Observable;
import java.util.Observer;
import b.observer.jv.WeatherData.Data;

//具体观察者类：预警条件
public class ForcastConditions implements Observer {
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
        System.out.println("***Tomorrow mTemperatrue:" +(mTemperatrue+1)+"***");
        System.out.println("***Tomorrow mPressure:" +(mPressure+1)+"***");
        System.out.println("***Tomorrow mHumidity:" +(mHumidity+1)+"***");
    }
}
