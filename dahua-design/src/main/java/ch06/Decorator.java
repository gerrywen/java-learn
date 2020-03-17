package ch06;

/**
 * program: java-learn->Decorator
 * description:
 * author: gerry
 * created: 2020-03-17 20:51
 **/
public abstract class Decorator extends Component {

    private Component component;

    public void setComponent(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        if (component != null) {
            component.operation();
        }
    }
}
