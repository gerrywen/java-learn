package ch21_02;

import java.util.ArrayList;

/**
 * program: java-learn->Leaf
 * description:
 * author: gerry
 * created: 2020-03-24 22:20
 **/
public class Leaf extends Component {
    @Override
    public void add(Component component) {
        //空实现,直接抛弃一个"不支持请求"异常
        throw new UnsupportedOperationException();
    }


    @Override
    public void remove(Component component) {
        //空实现,直接抛弃一个"不支持请求"异常
        throw new UnsupportedOperationException();
    }

    @Override
    public ArrayList<Component> getChildren() {
        //空实现,直接抛弃一个"不支持请求"异常
        throw new UnsupportedOperationException();
    }
}
