package ch24_01;


import java.util.HashMap;

/**
 * program: java-learn->Caretaker
 * description: 备忘录管理员角色
 * author: gerry
 * created: 2020-03-27 21:32
 **/
public class Caretaker {
    //容纳备忘录的容器
    private HashMap<String, Memento> memMap = new HashMap<String, Memento>();

    public Memento getMemento(String idx) {
        return memMap.get(idx);
    }

    public void setMemento(String idx, Memento memento) {
        this.memMap.put(idx, memento);
    }
}
