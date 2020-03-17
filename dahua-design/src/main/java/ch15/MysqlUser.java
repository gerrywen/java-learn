package ch15;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/3/17 9:36
 */
public class MysqlUser implements IUser {
    @Override
    public void insert(User user) {
        System.out.println("mysql 插入一条数据");
    }

    @Override
    public User getUserById(int userId) {
        System.out.println("mysql 获取一条数据");
        return null;
    }
}
