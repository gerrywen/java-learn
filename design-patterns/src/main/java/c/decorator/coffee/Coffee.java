package c.decorator.coffee;

import c.decorator.coffeebar.Drink;

public class Coffee extends Drink{
    @Override
    public float cost() {
        return super.getPrice();
    }
}
