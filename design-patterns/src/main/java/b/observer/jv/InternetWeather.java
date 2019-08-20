package b.observer.jv;

public class InternetWeather {
    public static void main(String[] args) {
        CurrentConditions mCurrentConditions; // 当前条件
        ForcastConditions mForcastConditions; // 预警条件
        WeatherData mWeatherData;

        mCurrentConditions=new CurrentConditions();
        mForcastConditions=new ForcastConditions();
        mWeatherData=new WeatherData();

        mWeatherData.addObserver(mCurrentConditions);
        mWeatherData.addObserver(mForcastConditions);
        mWeatherData.setData(30, 150, 40);

        mWeatherData.deleteObserver(mCurrentConditions); // 移除观察者
        mWeatherData.setData(35, 150, 60);
    }
}
