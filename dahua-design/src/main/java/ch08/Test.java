package ch08;

/**
 * program: java-learn->Test
 * description:
 * author: gerry
 * created: 2020-03-15 21:09
 **/
public class Test {

    public static void main(String[] args) {
        AddFactory operFactory = new AddFactory();
        Operation operation = operFactory.createOperation();
        operation.set_numberA(1.2);
        operation.set_numberB(2.5);
        double result = operation.getResult();
        System.out.println(result);
    }
}
