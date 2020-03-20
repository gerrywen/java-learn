package ch08_02;

/**
 * program: java-learn->YellowHuman
 * description:
 * author: gerry
 * created: 2020-03-20 20:53
 **/
public class YellowHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("黄色人种的皮肤颜色是黄色的！");
    }

    @Override
    public void talk() {
        System.out.println("黄色人种会说话，一般说的都是双字节。");
    }
}
