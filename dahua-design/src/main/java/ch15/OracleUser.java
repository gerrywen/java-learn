package ch15;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/3/17 9:37
 */
public class OracleUser implements IUser {
    @Override
    public void insert(User user) {
        System.out.println("oracle 插入一条 User 数据");
    }

    @Override
    public User getUserById(int userId) {
        System.out.println("oracle 获取一条 User 数据");
        return null;
    }
}
