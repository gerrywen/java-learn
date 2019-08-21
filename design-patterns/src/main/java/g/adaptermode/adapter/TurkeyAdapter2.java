package g.adaptermode.adapter;

import g.adaptermode.duck.Duck;
import g.adaptermode.turkey.WildTurkey;

/**
 * 类适配器模式的代码如下
 */
public class TurkeyAdapter2 extends WildTurkey implements Duck {
    @Override
    public void quack() {
        super.gobble();
    }

    @Override
    public void fly() {
        // TODO Auto-generated method stub
        super.fly();
        super.fly();
        super.fly();
    }
}
