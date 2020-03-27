package ch24_01;

/**
 * program: java-learn->Client
 * description:  场景类
 * author: gerry
 * created: 2020-03-27 22:02
 **/
public class Client {
    public static void main(String[] args) {
        // 定义出发起人
        Originator originator = new Originator();
        // 定义出备忘录管理员
        Caretaker caretaker = new Caretaker();
        //初始化
        originator.setState1("中国");
        originator.setState2("强盛");
        originator.setState3("繁荣");
        System.out.println("===初始化状态===\n" + originator);
        // 创建两个备忘录
        caretaker.setMemento("001", originator.createMemento());
        caretaker.setMemento("002", originator.createMemento());

        //修改状态值
        originator.setState1("软件");
        originator.setState2("架构");
        originator.setState3("优秀");
        System.out.println("\n===修改后状态===\n" + originator);

        // 恢复一个指定标记的备忘录
        originator.restoreMemento(caretaker.getMemento("001"));
        //恢复一个备忘录
        System.out.println("\n===恢复后状态===\n" + originator);
    }
}
