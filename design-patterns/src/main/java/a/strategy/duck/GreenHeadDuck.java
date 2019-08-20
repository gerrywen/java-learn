package a.strategy.duck;

import a.strategy.flybehavior.GoodFlyBehavior;
import a.strategy.quackbehavior.GaGaQuackBehavior;

public class GreenHeadDuck extends Duck {
    @Override
    public void display() {
        System.out.println("**GreenHead**");
    }
    public GreenHeadDuck() {
        mFlyBehavior = new GoodFlyBehavior();
        mQuackBehavior = new GaGaQuackBehavior();
    }

}
