package ch07_02;

/**
 * program: java-learn->Minister
 * description:
 * author: gerry
 * created: 2020-03-19 23:14
 **/
public class Minister {
    public static void main(String[] args) {
        // 定义5个大臣
        int ministerNum = 5;
        for (int i = 0; i < ministerNum; ++i) {
            Emperor emperor = Emperor.getInstance();
            System.out.print("第" + (i + 1) + "个大臣参拜的是：");
            emperor.say();
            System.out.println(System.identityHashCode(emperor));
        }
    }
}
