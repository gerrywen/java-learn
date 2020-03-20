package ch08_03;

/**
 * program: java-learn->AbstractHumanFactory
 * description: 抽象人类创建工厂
 * author: gerry
 * created: 2020-03-20 20:57
 **/
public abstract class AbstractHumanFactory {
    /**
     * 创建人
     * @return
     */
    public abstract Human createHuman();
}
