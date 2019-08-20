package c.decorator.decorator;

import c.decorator.coffeebar.Drink;

public class Soy extends Decorator {
    public Soy(Drink Obj) {
        super(Obj);
        super.setDescription("Soy");
        super.setPrice(1.5f);
    }
}
