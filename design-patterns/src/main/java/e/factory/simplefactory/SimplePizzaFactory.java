package e.factory.simplefactory;

import e.factory.pizza.CheesePizza;
import e.factory.pizza.GreekPizza;
import e.factory.pizza.PepperPizza;
import e.factory.pizza.Pizza;

public class SimplePizzaFactory {
    public Pizza CreatePizza(String ordertype) {
        Pizza pizza = null;

        if (ordertype.equals("cheese")) {
            pizza = new CheesePizza();
        } else if (ordertype.equals("greek")) {
            pizza = new GreekPizza();
        } else if (ordertype.equals("pepper")) {
            pizza = new PepperPizza();
        }
        return pizza;

    }
}
