package ch16_02;

/**
 * program: java-learn->FileLogger
 * description:
 * author: gerry
 * created: 2020-03-21 21:33
 **/
public class FileLogger extends AbstractLogger {
    public FileLogger(int _level) {
        super(_level);
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}
