package ch24;

/**
 * program: java-learn->Caretaker
 * description: 备忘录管理员角色
 * author: gerry
 * created: 2020-03-27 21:32
 **/
public class Caretaker {
    //发起人对象
    private Originator originator;

    public Originator getOriginator() {
        return originator;
    }

    public void setOriginator(Originator originator) {
        this.originator = originator;
    }
}
