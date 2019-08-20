package b.observer.jv;

import java.util.Observable;

/**
 * 具体目标类-数据中心
 */
public class WeatherData extends Observable {

    private float mTemperatrue;
    private float mPressure;
    private float mHumidity;

    public float getTemperature()
    {
        return mTemperatrue;

    }

    public float getPressure()
    {
        return mPressure;

    }

    public float getHumidity()
    {
        return mHumidity;

    }

    /**
     * 定义数据类
     */
    public class Data
    {
        public float mTemperatrue;
        public float mPressure;
        public float mHumidity;
        public Data(float mTemperatrue,float mPressure,float mHumidity)
        {
            this.mTemperatrue=mTemperatrue;
            this.mPressure=mPressure;
            this.mHumidity=mHumidity;
        }
    }

    /**
     * 传入数据
     * @param mTemperatrue 温度
     * @param mPressure 气压
     * @param mHumidity 湿度
     */
    public void setData(float mTemperatrue,float mPressure,float mHumidity)
    {
        this.mTemperatrue=mTemperatrue;
        this.mPressure=mPressure;
        this.mHumidity=mHumidity;
        dataChange();
    }

    /**
     * 通知变化
     */
    public void dataChange()
    {
        //设置内部标志位，注明数据发生变化
        this.setChanged();
        // 通知数据变化
        this.notifyObservers(new Data(getTemperature(),getPressure(),getHumidity()));

    }
}
