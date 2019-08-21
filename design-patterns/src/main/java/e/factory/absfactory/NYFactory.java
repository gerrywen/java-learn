package e.factory.absfactory;

import e.factory.pizza.NYCheesePizza;
import e.factory.pizza.NYPepperPizza;
import e.factory.pizza.Pizza;

public class NYFactory implements AbsFactory {
    @Override
    public Pizza CreatePizza(String ordertype) {
        Pizza pizza = null;

        if (ordertype.equals("cheese")) {
            pizza = new NYCheesePizza();
        } else if (ordertype.equals("pepper")) {
            pizza = new NYPepperPizza();
        }
        return pizza;
    }
}
