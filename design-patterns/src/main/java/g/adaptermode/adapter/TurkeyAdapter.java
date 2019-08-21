package g.adaptermode.adapter;

import g.adaptermode.duck.Duck;
import g.adaptermode.turkey.Turkey;

/**
 * 对象适配器模式的代码如下。
 */
public class TurkeyAdapter implements Duck {
    private Turkey turkey;

    public TurkeyAdapter(Turkey turkey)
    {
        this.turkey=turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        for(int i=0;i<6;i++)
        {
            turkey.fly();
        }
    }
}
