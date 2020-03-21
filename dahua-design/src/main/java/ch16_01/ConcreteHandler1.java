package ch16_01;

/**
 * program: java-learn->ConcreteHandler1
 * description:
 * author: gerry
 * created: 2020-03-21 20:46
 **/
public class ConcreteHandler1 extends Handler {
    //设置自己的处理级别
    @Override
    protected Level getHandlerLevel() {
        //设置自己的处理级别
        Level level = new Level();
        level.setLevel(1);
        return level;
    }

    // 定义自己的处理逻辑
    @Override
    protected Response echo(Request request) {
        //完成处理逻辑
        return null;
    }
}
