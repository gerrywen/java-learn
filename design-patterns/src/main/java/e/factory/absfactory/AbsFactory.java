package e.factory.absfactory;

import e.factory.pizza.Pizza;

public interface AbsFactory {
    public Pizza CreatePizza(String ordertype);
}

