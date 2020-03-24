package ch20;

import java.util.ArrayList;

/**
 * program: java-learn->ProjectIterator
 * description:
 * author: gerry
 * created: 2020-03-24 21:06
 **/
public class ProjectIterator implements IProjectIterator {
    //所有的项目都放在ArrayList中
    private ArrayList<IProject> projectList = new ArrayList<IProject>();
    // 标记当前位置
    private int currentItem = 0;

    // 构造函数传入projectList
    public ProjectIterator(ArrayList<IProject> projectList) {
        this.projectList = projectList;
    }

    //判断是否还有元素，必须实现
    @Override
    public boolean hasNext() {
        //定义一个返回值
        boolean b = true;
        // 判断是否还有元素
        if (this.currentItem >= projectList.size() || this.projectList.get(this.currentItem) == null) {
            b = false;
        }
        return b;
    }

    //取得下一个值
    @Override
    public IProject next() {
        return (IProject)this.projectList.get(this.currentItem++);
    }

    //删除一个对象
    public void remove() { //暂时没有使用到
    }


}
