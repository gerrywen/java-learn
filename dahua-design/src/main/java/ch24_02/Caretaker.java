package ch24_02;

/**
 * program: java-learn->Caretaker
 * description:
 * author: gerry
 * created: 2020-03-27 22:40
 **/
public class Caretaker {
    //备忘录对象
    private IMemento memento;

    public IMemento getMemento() {
        return memento;
    }

    public void setMemento(IMemento memento) {
        this.memento = memento;
    }
}
