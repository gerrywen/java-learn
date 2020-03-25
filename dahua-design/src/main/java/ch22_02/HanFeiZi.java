package ch22_02;

import java.util.ArrayList;


/**
 * program: java-learn->HanFeiZi
 * description: 被观察者实现类
 * author: gerry
 * created: 2020-03-25 21:55
 **/
public class HanFeiZi implements IHanFeiZi, Observable {
    /**
     * 定义个变长数组，存放所有的观察者
     */
    private ArrayList<Observer> observerList = new ArrayList<>();


    /**
     * 增加观察者
     */
    @Override
    public synchronized void addObserver(Observer observer) {
        this.observerList.add(observer);
    }

    /**
     * 删除观察者
     *
     * @param observer
     */
    @Override
    public synchronized void deleteObserver(Observer observer) {
        this.observerList.remove(observer);
    }

    /**
     * 通知所有的观察者
     *
     * @param context
     */
    @Override
    public void notifyObservers(String context) {
        observerList.parallelStream()
                .forEach(observer -> observer.update(context));
    }

    /**
     * 韩非子要吃饭了
     */
    @Override
    public void haveBreakfast() {
        System.out.println("韩非子:开始吃饭了...");
        //通知所有的观察者
        this.notifyObservers("韩非子在吃饭");
    }

    /**
     * 韩非子开始娱乐了
     */
    @Override
    public void haveFun() {
        System.out.println("韩非子:开始娱乐了...");
        this.notifyObservers("韩非子在娱乐");
    }

}
