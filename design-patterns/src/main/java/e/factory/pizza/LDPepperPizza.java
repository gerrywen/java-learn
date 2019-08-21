package e.factory.pizza;

public class LDPepperPizza extends Pizza {
    @Override
    public void prepare() {
        super.setname("LDPepperPizza");

        System.out.println(name+" preparing;");
    }
}
