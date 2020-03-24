package ch21_01;

/**
 * program: java-learn->Corp
 * description: 抽象公司职员类
 * author: gerry
 * created: 2020-03-24 21:49
 **/
public abstract class Corp {
    //叫什么名字
    private String name = "";
    //职位
    private String position = "";
    //薪水
    private int salary = 0;

    public Corp(String _name, String _position, int _salary) {
        this.name = _name;
        this.position = _position;
        this.salary = _salary;
    }

    // 获得员工信息
    public String getInfo() {
        String info = "";
        info = "姓名：" + this.name;
        info = info + "\t职位：" + this.position;
        info = info + "\t薪水：" + this.salary;
        return info;
    }
}
