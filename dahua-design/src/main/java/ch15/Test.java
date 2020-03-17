package ch15;


/**
 * program: java-learn->Test
 * description:
 * author: gerry
 * created: 2020-03-16 22:31
 **/
public class Test {

    public static void main(String[] args) {
        String driver = PropertyUtil.createResourcePath().getProperty("jdbc.driver");
        System.out.println(driver);
    }
}
