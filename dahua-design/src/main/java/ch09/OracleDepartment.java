package ch09;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/3/17 10:00
 */
public class OracleDepartment implements IDepartment {
    @Override
    public void insert(Department department) {
        System.out.println("oracle 插入一条 Department 数据");
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        System.out.println("oracle 获取一条 Department 数据");
        return null;
    }
}
