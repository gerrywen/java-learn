package ch15;

/**
 * program: java-learn->Invoker
 * description:  负责人
 * author: gerry
 * created: 2020-03-21 17:33
 **/
public class Invoker {
    //什么命令
    private Command command;

    //客户发出命令
    public void setCommand(Command command) {
        this.command = command;
    }

    //执行客户的命令
    public void action() {
        this.command.execute();
    }
}
