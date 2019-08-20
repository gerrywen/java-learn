package b.observer.mode;

import b.observer.observer.Observer;
import b.observer.observer.Subject;

import java.util.ArrayList;

public class WeatherDataSt implements Subject {
    private float mTemperatrue;
    private float mPressure;
    private float mHumidity;
    private ArrayList<Observer> mObservers;

    public WeatherDataSt() {
        mObservers = new ArrayList<Observer>();
    }

    public float getTemperature() {
        return mTemperatrue;

    }

    public float getPressure() {
        return mPressure;

    }

    public float getHumidity() {
        return mHumidity;

    }

    public void dataChange() {
        notifyObservers();
    }

    public void setData(float mTemperatrue, float mPressure, float mHumidity) {
        this.mTemperatrue = mTemperatrue;
        this.mPressure = mPressure;
        this.mHumidity = mHumidity;
        dataChange();
    }

    @Override
    public void registerObserver(Observer o) {
        mObservers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if (mObservers.contains(o)) {
            mObservers.remove(o);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer mObserver : mObservers) {
            mObserver.update(getTemperature(), getPressure(), getHumidity());
        }
    }
}
