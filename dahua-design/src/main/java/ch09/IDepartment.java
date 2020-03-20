package ch09;

/**
 * description: 操作数据库Department表的接口
 *
 * @author wenguoli
 * @date 2020/3/17 9:59
 */
public interface IDepartment {
    /**
     * 插入数据
     * @param department
     */
    void insert(Department department);

    /**
     * 根据主键获取数据
     * @param departmentId
     * @return
     */
    Department getDepartmentById(int departmentId);
}
