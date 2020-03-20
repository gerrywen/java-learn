package ch08_03;

/**
 * program: java-learn->YellowHumanFactory
 * description:
 * author: gerry
 * created: 2020-03-20 21:36
 **/
public class YellowHumanFactory extends AbstractHumanFactory {
    @Override
    public Human createHuman() {
        return new YellowHuman();
    }
}
