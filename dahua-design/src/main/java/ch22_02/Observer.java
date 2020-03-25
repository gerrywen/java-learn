package ch22_02;

/**
 * program: java-learn->Observer
 * description: 观察者接口
 * author: gerry
 * created: 2020-03-25 22:01
 **/
public interface Observer {
    /**
     * 一发现别人有动静，自己也要行动起来
     * @param context
     */
    public void update(String context);
}
