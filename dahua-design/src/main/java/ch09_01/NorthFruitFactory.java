package ch09_01;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/3/17 10:24
 */
public class NorthFruitFactory implements IFruitFactory {
    @Override
    public IFruit getApple() {
        return new NorthApple();
    }

    @Override
    public IFruit getBanana() {
        return new NorthBanana();
    }
}
