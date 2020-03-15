package ch01;

/**
 * program: java-learn->OperationFactory
 * description:
 * author: gerry
 * created: 2020-03-15 20:12
 **/
public class OperationFactory {

    public static Operation createOperation(String operate){
        Operation operation = null;
        switch (operate) {
            case "+":
                operation = new OperationAdd();
                break;
            case "-":
                operation = new OperationSub();
                break;
            case "*":
                operation = new OperationMul();
                break;
            case "/":
                operation = new OperationDiv();
                break;
        }
        return operation;
    }
}
