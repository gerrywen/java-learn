package ch17_01;

/**
 * program: java-learn->Finery
 * description: 服饰
 * author: gerry
 * created: 2020-03-17 21:28
 **/
public class Finery extends Person{

    private Person component;

    public void Decorate(Person component) {
        this.component = component;
    }

    @Override
    public void show() {
        if (component != null) {
            component.show();
        }
    }
}
