package ch08_03;

/**
 * program: java-learn->WhiteHumanFactory
 * description:
 * author: gerry
 * created: 2020-03-20 21:36
 **/
public class WhiteHumanFactory extends AbstractHumanFactory {
    @Override
    public Human createHuman() {
        return new WhiteHuman();
    }
}
