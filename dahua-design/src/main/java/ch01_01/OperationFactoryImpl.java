package ch01_01;

/**
 * program: java-learn->OperationFactoryImpl
 * description:
 * author: gerry
 * created: 2020-03-15 20:30
 **/
public class OperationFactoryImpl implements OperationFactory {

    @Override
    public Operation createOperation(String operate){
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
