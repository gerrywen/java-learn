package c.decorator.decorator;

import c.decorator.coffeebar.Drink;

public class Decorator extends Drink {
    private Drink Obj;

    public Decorator(Drink Obj) {
        this.Obj = Obj;
    }

    @Override
    public String getDescription() {
        return super.description + "-" + super.getPrice() + "&&" + Obj.getDescription();
    }

    @Override
    public float cost() {
        return super.getPrice() + Obj.cost();
    }
}
