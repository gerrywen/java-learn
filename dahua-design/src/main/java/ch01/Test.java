package ch01;

/**
 * program: java-learn->Test
 * description:
 * author: gerry
 * created: 2020-03-15 20:20
 **/
public class Test {
    public static void main(String[] args) {
        Operation operation = OperationFactory.createOperation("+");
        operation.set_numberA(1.2);
        operation.set_numberB(2.5);
        double result = operation.getResult();
        System.out.println(result);
    }
}
