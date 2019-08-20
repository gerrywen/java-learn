package b.observer.observer;

/**
 * 抽象观察者
 */
public interface Observer {
    /**
     * 更新方法
     * @param mTemperatrue 温度
     * @param mPressure 气压
     * @param mHumidity 湿度
     */
    public void update(float mTemperatrue,float mPressure,float mHumidity);
}
