package ch24;

/**
 * program: java-learn->Client
 * description: 场景类
 * author: gerry
 * created: 2020-03-27 21:41
 **/
public class Client {
    public static void main(String[] args) {
        // 定义发起人
        Originator originator = new Originator();
        // 建立初始状态
        originator.setState("初始状态...");
        System.out.println("初始状态是：" + originator.getState());
        // 建立备份
        originator.createMemento();
        //修改状态
        originator.setState("修改后的状态...");
        System.out.println("修改后状态是：" + originator.getState());
        //恢复原有状态
        originator.restoreMemento();
        System.out.println("恢复后状态是：" + originator.getState());

        originator.restoreMemento();
        System.out.println("恢复后状态是：" + originator.getState());
    }
}
