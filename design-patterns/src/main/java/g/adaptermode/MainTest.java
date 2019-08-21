package g.adaptermode;

import g.adaptermode.adapter.TurkeyAdapter;
import g.adaptermode.adapter.TurkeyAdapter2;
import g.adaptermode.duck.Duck;
import g.adaptermode.duck.GreenHeadDuck;
import g.adaptermode.turkey.WildTurkey;

public class MainTest {
    public static void main(String[] args) {
        GreenHeadDuck duck=new GreenHeadDuck();

        WildTurkey turkey=new WildTurkey();

        Duck duck2turkeyAdapter=new TurkeyAdapter2();
        turkey.gobble();
        turkey.fly();
        duck.quack();
        duck.fly();
        duck2turkeyAdapter.quack();
        duck2turkeyAdapter.fly();


        TurkeyAdapter turkeyAdapter = new TurkeyAdapter(turkey);
        turkeyAdapter.quack();
        turkeyAdapter.fly();

    }
}
