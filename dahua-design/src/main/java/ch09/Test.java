package ch09;


import ch09_01.PropertyUtil;

/**
 * program: java-learn->Test
 * description:
 * author: gerry
 * created: 2020-03-16 22:31
 **/
public class Test {

    public static void main(String[] args) {

    }


    /**
     * 普通工厂模式
     */
    public void factoryDemo() {
        IFactory factory = new MysqlFactory();  //如果需要修改数据库，则只需将new MysqlFactory()修改为new OracleFactory()。
        IUser iu = factory.createUser();
        iu.insert(new User());
        iu.getUserById(1);


        IFactory oracleFactory = new OracleFactory();  //如果需要修改数据库，则只需将new MysqlFactory()修改为new OracleFactory()。
        IUser oracleIu = oracleFactory.createUser();
        oracleIu.insert(new User());
        oracleIu.getUserById(1);
    }

    public void test1() {
        String driver = PropertyUtil.createResourcePath().getProperty("jdbc.driver");
        System.out.println(driver);
    }
}
