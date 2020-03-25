package ch22_02;

/**
 * program: java-learn->Observable
 * description:  被观察者接口
 * author: gerry
 * created: 2020-03-25 21:53
 **/
public interface Observable {

    /**
     * 增加一个观察者
     * @param observer
     */
    public void addObserver(Observer observer);

    /**
     * 删除一个观察者
     * @param observer
     */
    public void deleteObserver(Observer observer);

    /**
     * 既然要观察，我发生改变了他也应该有所动作，通知观察者
     * @param context
     */
    public void notifyObservers(String context);
}
