package ch20;

/**
 * program: java-learn->IProject
 * description: 项目信息接口
 * author: gerry
 * created: 2020-03-24 21:03
 **/
public interface IProject {
    //增加项目
    public void add(String name, int num, int cost);

    //从老板这里看到的就是项目信息
    public String getProjectInfo();

    //获得一个可以被遍历的对象
    public IProjectIterator iterator();
}
