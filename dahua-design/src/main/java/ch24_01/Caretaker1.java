package ch24_01;


/**
 * program: java-learn->Caretaker
 * description: 备忘录管理员角色
 * author: gerry
 * created: 2020-03-27 21:32
 **/
public class Caretaker1 {
    //发起人对象
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
