package ch15;

/**
 * description:
 *
 * @author wenguoli
 * @date 2020/3/17 10:00
 */
public class MysqlDepartment implements IDepartment {
    @Override
    public void insert(Department department) {
        System.out.println("mysql 插入一条 Department 数据");
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        System.out.println("mysql 获取一条 Department 数据");
        return null;
    }
}
