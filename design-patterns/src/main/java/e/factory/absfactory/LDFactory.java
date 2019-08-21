package e.factory.absfactory;

import e.factory.pizza.LDCheesePizza;
import e.factory.pizza.LDPepperPizza;
import e.factory.pizza.Pizza;

public class LDFactory implements AbsFactory {
    @Override
    public Pizza CreatePizza(String ordertype) {
        Pizza pizza = null;

        if (ordertype.equals("cheese")) {
            pizza = new LDCheesePizza();
        } else if (ordertype.equals("pepper")) {
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}
