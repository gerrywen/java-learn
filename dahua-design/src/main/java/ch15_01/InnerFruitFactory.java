package ch15_01;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/3/17 10:19
 */
public class InnerFruitFactory implements IFruitFactory {
    @Override
    public IFruit getApple() {
        return new InnerApple();
    }

    @Override
    public IFruit getBanana() {
        return new InnerBanana();
    }
}
