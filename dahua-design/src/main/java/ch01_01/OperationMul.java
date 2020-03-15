package ch01_01;

/**
 * program: java-learn->OperationAdd
 * description:
 * author: gerry
 * created: 2020-03-15 20:07
 **/
public class OperationMul extends Operation {
    @Override
    public double getResult() {
        return get_numberA() * get_numberB();
    }
}
