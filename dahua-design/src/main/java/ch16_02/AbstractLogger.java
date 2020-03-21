package ch16_02;

/**
 * program: java-learn->AbstractLogger
 * description:
 * author: gerry
 * created: 2020-03-21 21:26
 **/
public abstract class AbstractLogger {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    //能处理的级别
    protected int level = 1;

    //责任链中的下一个元素
    protected AbstractLogger nextLogger;


    //每个类都要说明一下自己能处理哪些请求
    public AbstractLogger(int _level) {
        this.level = _level;
    }

    // 设置下一个责任链
    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    // 日志
    public void logMessage(int level, String message) {
        if(this.level <= level){
            write(message);
        }
        // 下个责任链存在，继续往后传递
        if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }

    // 模板方法，实现写
    protected abstract void write(String message);

}
