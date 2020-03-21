package ch16_02;

/**
 * program: java-learn->ErrorLogger
 * description:
 * author: gerry
 * created: 2020-03-21 21:32
 **/
public class ErrorLogger extends AbstractLogger {
    public ErrorLogger(int _level) {
        super(_level);
    }

    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}
