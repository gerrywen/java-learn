package b.observer.observer;

public interface Subject {
    /**
     * 注册
     * @param o 抽象观察者
     */
    public void registerObserver(Observer o);

    /**
     * 移除
     * @param o 抽象观察者
     */
    public void removeObserver(Observer o);

    /**
     * 通知
     */
    public void notifyObservers();
}
