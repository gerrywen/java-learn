package ch11;

import java.util.ArrayList;

/**
 * program: java-learn->BenzBuilder
 * description:
 * author: gerry
 * created: 2020-03-21 12:22
 **/
public class BenzBuilder extends CarBuilder {
    private BenzModel benz = new BenzModel();

    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.benz.setSequence(sequence);
    }

    @Override
    public CarModel getCarModel() {
        return this.benz;
    }
}
