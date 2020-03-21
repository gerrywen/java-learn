package ch16;

/**
 * program: java-learn->IWomen
 * description: 女性接口
 * author: gerry
 * created: 2020-03-21 20:03
 **/
public interface IWomen {
    // 获得个人状况
    public int getType();

    //获得个人请示，你要干什么？出去逛街？约会?还是看电影？
    public String getRequest();
}
