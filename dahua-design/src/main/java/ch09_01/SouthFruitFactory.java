package ch09_01;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/3/17 10:25
 */
public class SouthFruitFactory implements IFruitFactory {
    @Override
    public IFruit getApple() {
        return new SouthApple();
    }

    @Override
    public IFruit getBanana() {
        return new SouthBanana();
    }
}
