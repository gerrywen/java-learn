package ch15;

/**
 * description: 操作数据库User表的接口
 *
 * @author wenguoli
 * @date 2020/3/17 9:32
 */
public interface IUser {
    /**
     * 插入数据
     * @param user
     */
    void insert(User user);

    /**
     * 根据主键获取数据
     * @param userId
     * @return
     */
    User getUserById(int userId);
}
