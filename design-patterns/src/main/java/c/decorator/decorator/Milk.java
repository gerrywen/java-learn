package c.decorator.decorator;

import c.decorator.coffeebar.Drink;

public class Milk extends Decorator {
    public Milk(Drink Obj) {
        super(Obj);
        super.setDescription("Milk");
        super.setPrice(2.0f);
    }
}
