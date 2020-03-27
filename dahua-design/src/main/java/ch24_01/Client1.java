package ch24_01;

/**
 * program: java-learn->Client
 * description:  场景类
 * author: gerry
 * created: 2020-03-27 22:02
 **/
public class Client1 {
    public static void main(String[] args) {
        //定义出发起人
        Originator ori = new Originator();
        //定义出备忘录管理员
        Caretaker1 caretaker = new Caretaker1();
        //初始化
        ori.setState1("中国");
        ori.setState2("强盛");
        ori.setState3("繁荣");
        System.out.println("===初始化状态===\n" + ori);
        //创建一个备忘录
        caretaker.setMemento(ori.createMemento());
        //修改状态值
        ori.setState1("软件");
        ori.setState2("架构");
        ori.setState3("优秀");
        System.out.println("\n===修改后状态===\n" + ori);
        //恢复一个备忘录
        ori.restoreMemento(caretaker.getMemento());
        System.out.println("\n===恢复后状态===\n" + ori);
    }
}
