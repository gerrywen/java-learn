package ch09_01;

/**
 * description: 水果工厂类
 *
 * @author wenguoli
 * @date 2020/3/17 10:17
 */
public interface IFruitFactory {

    /**
     * 实例化Apple
     * @return
     */
    public IFruit getApple();

    /**
     * 实例化Banana
     * @return
     */
    public IFruit getBanana();
}
