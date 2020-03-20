package ch08_02;

/**
 * program: java-learn->HumanFactory
 * description:
 * author: gerry
 * created: 2020-03-20 20:58
 **/
public class HumanFactory {
    public static  <T extends Human> T createHuman(Class<T> c) {
        // 定义一个生产的人种
        Human human = null;
        try {
            human = (T) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            System.out.println("人种生成错误！");
        }
        return (T) human;
    }
}
