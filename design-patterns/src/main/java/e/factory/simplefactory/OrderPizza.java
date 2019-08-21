package e.factory.simplefactory;

import e.factory.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderPizza {
    SimplePizzaFactory mSimplePizzaFactory;

    public OrderPizza(SimplePizzaFactory mSimplePizzaFactory) {
        setFactory(mSimplePizzaFactory);
    }


    /**
     * 设置简单工厂类
     * @param mSimplePizzaFactory 简单工厂类
     */
    public void setFactory(SimplePizzaFactory mSimplePizzaFactory) {
        Pizza pizza = null;
        String ordertype;
        this.mSimplePizzaFactory = mSimplePizzaFactory;

        do {

            ordertype = gettype();
            pizza = mSimplePizzaFactory.CreatePizza(ordertype);
            if (pizza != null) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            }
        } while (true);
    }

    /**
     * 获取类型
     *
     * @return 字符串
     */
    private String gettype() {
        try {
            BufferedReader strin = new BufferedReader(new InputStreamReader(
                    System.in));
            System.out.println("input pizza type:");
            String str = strin.readLine();

            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
