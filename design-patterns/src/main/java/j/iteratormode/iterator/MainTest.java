package j.iteratormode.iterator;

public class MainTest {
    public static void main(String[] args) {
        Waitress waitress = new Waitress();
        CakeHouseMenu cakeHouseMenu = new CakeHouseMenu();
        DinerMenu dinerMenu = new DinerMenu();

        waitress.addIterator(cakeHouseMenu.getIterator());
        waitress.addIterator(dinerMenu.getIterator());
        waitress.printMenu();
    }
}
