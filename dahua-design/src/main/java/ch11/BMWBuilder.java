package ch11;

import java.util.ArrayList;

/**
 * program: java-learn->BMWBuilder
 * description:
 * author: gerry
 * created: 2020-03-21 12:23
 **/
public class BMWBuilder extends CarBuilder {
    private BMWModel bmw = new BMWModel();

    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.bmw.setSequence(sequence);
    }

    @Override
    public CarModel getCarModel() {
        return this.bmw;
    }
}
