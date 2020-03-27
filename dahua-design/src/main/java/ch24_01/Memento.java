package ch24_01;

import java.util.HashMap;

/**
 * program: java-learn->Memento
 * description: 备忘录角色
 * author: gerry
 * created: 2020-03-27 21:53
 **/
public class Memento {
    // 接受HashMap作为状态
    private HashMap<String, Object> stateMap;

    // 接受一个对象，建立一个备份
    public Memento(HashMap<String, Object> map) {
        this.stateMap = map;
    }

    public HashMap<String, Object> getStateMap() {
        return stateMap;
    }

    public void setStateMap(HashMap<String, Object> stateMap) {
        this.stateMap = stateMap;
    }
}
