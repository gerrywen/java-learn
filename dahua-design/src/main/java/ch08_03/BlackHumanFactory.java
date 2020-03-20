package ch08_03;

/**
 * program: java-learn->BlackHumanFactory
 * description:
 * author: gerry
 * created: 2020-03-20 21:35
 **/
public class BlackHumanFactory extends AbstractHumanFactory {
    @Override
    public Human createHuman() {
        return new BlackHuman();
    }
}
