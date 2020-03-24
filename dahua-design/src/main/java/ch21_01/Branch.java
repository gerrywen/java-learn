package ch21_01;

import java.util.ArrayList;

/**
 * program: java-learn->Branch
 * description:
 * author: gerry
 * created: 2020-03-24 21:51
 **/
public class Branch extends Corp {
    public Branch(String _name, String _position, int _salary) {
        super(_name, _position, _salary);
    }

    //领导下边有哪些下级领导和小兵
    ArrayList<Corp> subordinateList = new ArrayList<Corp>();

    //增加一个下属，可能是小头目，也可能是个小兵
    public void addSubordinate(Corp corp) {
        this.subordinateList.add(corp);
    }

    //我有哪些下属
    public ArrayList<Corp> getSubordinate() {
        return this.subordinateList;
    }
}
