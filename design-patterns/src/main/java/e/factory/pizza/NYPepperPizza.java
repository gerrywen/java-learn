package e.factory.pizza;

public class NYPepperPizza extends Pizza{

    @Override
    public void prepare() {
        super.setname("NYPepperPizza");

        System.out.println(name+" preparing;");
    }
}
