package ch09;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/3/17 9:37
 */
public class MysqlFactory implements IFactory {
    @Override
    public IUser createUser() {
        return new MysqlUser();
    }

    @Override
    public IDepartment createDepartment() {
        return new MysqlDepartment();
    }
}
