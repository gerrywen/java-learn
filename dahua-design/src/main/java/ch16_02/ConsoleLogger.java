package ch16_02;

/**
 * program: java-learn->ConsoleLogger
 * description:
 * author: gerry
 * created: 2020-03-21 21:29
 **/
public class ConsoleLogger extends AbstractLogger {
    public ConsoleLogger(int _level) {
        super(_level);
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}
