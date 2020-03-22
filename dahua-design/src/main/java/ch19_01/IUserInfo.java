package ch19_01;

/**
 * program: java-learn->IUserInfo
 * description: 员工信息接口
 * author: gerry
 * created: 2020-03-22 09:02
 **/
public interface IUserInfo {
    //获得用户姓名
    public String getUserName();

    //获得家庭地址
    public String getHomeAddress();

    //手机号码，这个太重要，手机泛滥呀
    public String getMobileNumber();

    //办公电话，一般是座机
    public String getOfficeTelNumber();

    //这个人的职位是什么
    public String getJobPosition();

    //获得家庭电话，这有点不好，我不喜欢打家庭电话讨论工作
    public String getHomeTelNumber();
}
