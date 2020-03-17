package ch15;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/3/17 9:38
 */
public class OracleFactory implements IFactory {
    @Override
    public IUser createUser() {
        return new OracleUser();
    }

    @Override
    public IDepartment createDepartment() {
        return new OracleDepartment();
    }
}
