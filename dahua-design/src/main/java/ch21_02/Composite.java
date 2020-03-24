package ch21_02;

import java.util.ArrayList;

/**
 * program: java-learn->Composite
 * description:
 * author: gerry
 * created: 2020-03-24 22:26
 **/
public class Composite extends Component {
    //构件容器
    private ArrayList<Component> componentArrayList = new ArrayList<Component>();

    @Override
    public void add(Component component) {
        this.componentArrayList.add(component);
    }

    @Override
    public void remove(Component component) {
        this.componentArrayList.remove(component);
    }

    @Override
    public ArrayList<Component> getChildren() {
        return this.componentArrayList;
    }
}
