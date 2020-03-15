package ch08;

/**
 * program: java-learn->Operation
 * description:
 * author: gerry
 * created: 2020-03-15 20:03
 **/
abstract public class Operation {


    private double _numberA = 0;

    private double _numberB = 0;


    public abstract double getResult();


    public double get_numberA() {
        return _numberA;
    }

    public void set_numberA(double _numberA) {
        this._numberA = _numberA;
    }

    public double get_numberB() {
        return _numberB;
    }

    public void set_numberB(double _numberB) {
        this._numberB = _numberB;
    }

}
