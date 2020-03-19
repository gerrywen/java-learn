package ch07;

/**
 * program: java-learn->Minister
 * description:
 * author: gerry
 * created: 2020-03-19 22:46
 **/
public class Minister {
    public static void main(String[] args) {
        // 三天见的皇帝都是同一个人，荣幸吧！
        for (int i = 0; i < 3; ++i) {
            Emperor emperor = Emperor.getInstance();
            emperor.say();
            int i1 = System.identityHashCode(emperor);
            System.out.println(i1);
        }
    }
}
