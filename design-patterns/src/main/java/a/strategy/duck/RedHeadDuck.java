package a.strategy.duck;

import a.strategy.flybehavior.BadFlyBehavior;
import a.strategy.quackbehavior.GeGeQuackBehavior;

public class RedHeadDuck extends Duck {
    @Override
    public void display() {
        System.out.println("**RedHead**");
    }

    public RedHeadDuck() {
        mFlyBehavior = new BadFlyBehavior();
        mQuackBehavior = new GeGeQuackBehavior();
    }
}
