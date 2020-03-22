package ch19;

/**
 * program: java-learn->Client
 * description:
 * author: gerry
 * created: 2020-03-22 09:12
 **/
public class Client {
    public static void main(String[] args) {
        // 类适配 获取用户信息
        IUserInfo youngGirl = new OuterUserInfo();
        //从数据库中查到101个
        for (int i = 0; i < 101; i++) {
            youngGirl.getMobileNumber();
        }
    }
}
