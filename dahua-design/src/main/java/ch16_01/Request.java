package ch16_01;

/**
 * program: java-learn->Request
 * description:
 * author: gerry
 * created: 2020-03-21 20:40
 **/
public class Request {
    //定义一个请求和处理等级
    private int level = 0;

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    //请求的等级
    public Level getRequestLevel() {
        System.out.println("请求的等级:" + this.level);
        Level level = new Level();
        level.setLevel(this.level);
        return level;
    }
}
