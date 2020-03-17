package ch06;

/**
 * program: java-learn->ConcreteComponent
 * description:
 * author: gerry
 * created: 2020-03-17 20:50
 **/
public class ConcreteComponent extends Component {
    @Override
    public void operation() {
        System.out.println("具体对象的操作");
    }
}
