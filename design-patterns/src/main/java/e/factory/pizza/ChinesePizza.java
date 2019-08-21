package e.factory.pizza;

public class ChinesePizza extends Pizza {
    @Override
    public void prepare() {
        super.setname("ChinesePizza");

        System.out.println(name+" preparing;");
    }
}
