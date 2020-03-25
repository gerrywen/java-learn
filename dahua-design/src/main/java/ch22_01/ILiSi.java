package ch22_01;

/**
 * program: java-learn->ILiSi
 * description: 抽象观察者
 * author: gerry
 * created: 2020-03-25 21:18
 **/
public interface ILiSi {
    /**
     * 一发现别人有动静，自己也要行动起来
     * @param context
     */
    public void update(String context);
}
