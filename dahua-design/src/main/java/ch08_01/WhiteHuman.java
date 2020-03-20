package ch08_01;

/**
 * program: java-learn->WhiteHuman
 * description:
 * author: gerry
 * created: 2020-03-20 20:56
 **/
public class WhiteHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("白色人种的皮肤颜色是白色的！");
    }

    @Override
    public void talk() {
        System.out.println("白色人种会说话，一般都是但是单字节。");
    }
}
