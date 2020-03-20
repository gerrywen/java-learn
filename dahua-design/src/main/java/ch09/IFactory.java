package ch09;

/**
 * description: 数据库工厂类
 *
 * @author wenguoli
 * @date 2020/3/17 9:31
 */
public interface IFactory {
    /**
     * 创建用户
     * @return
     */
    IUser createUser();

    /**
     * 创建部门
     * @return
     */
    IDepartment createDepartment();
}
