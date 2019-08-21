package e.factory.method;

import e.factory.pizza.NYCheesePizza;
import e.factory.pizza.NYPepperPizza;
import e.factory.pizza.Pizza;

public class NYOrderPizza extends OrderPizza {
    @Override
    Pizza createPizza(String ordertype) {
        Pizza pizza = null;

        if (ordertype.equals("cheese")) {
            pizza = new NYCheesePizza();
        } else if (ordertype.equals("pepper")) {
            pizza = new NYPepperPizza();
        }
        return pizza;
    }
}
