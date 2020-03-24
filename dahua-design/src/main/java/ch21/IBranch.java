package ch21;

import java.util.ArrayList;

/**
 * program: java-learn->IBranch
 * description: 树枝接口
 * author: gerry
 * created: 2020-03-24 21:32
 **/
public interface IBranch extends ICorp{
    //能够增加小兵（树叶节点）或者是经理（树枝节点）
    public void addSubordinate(ICorp corp);

    //我还要能够获得下属的信息
    public ArrayList<ICorp> getSubordinate();

    /**
     * 本来还应该有一个方法delSubordinate(ICorp corp)，删除下属
     * 这个方法我们没有用到就不写进来了
     */
}
