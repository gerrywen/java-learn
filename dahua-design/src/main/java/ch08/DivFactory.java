package ch08;

/**
 * program: java-learn->AddFactory
 * description:
 * author: gerry
 * created: 2020-03-15 21:07
 **/
public class DivFactory implements IFactory {
    @Override
    public Operation createOperation() {
        return new OperationDiv();
    }
}
