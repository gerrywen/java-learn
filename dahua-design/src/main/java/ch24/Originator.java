package ch24;

/**
 * program: java-learn->Originator
 * description: 融合备忘录的发起人角色
 * author: gerry
 * created: 2020-03-27 21:13
 **/
public class Originator implements Cloneable {
    // 备份
    private Originator backup;

    //内部状态
    private String state = "";

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * 创建一个备忘录
     * @return
     */
    public Originator createMemento(){
        return this.backup = this.clone();
    }

    /**
     * 恢复一个备忘录
     */
    public void restoreMemento(){
        // 在进行恢复前应该进行断言，防止空指针
        this.setState(this.backup.getState());
    }

    /**
     * 克隆当前对象
     * @return
     */
    @Override
    protected Originator clone() {
        try {
            return (Originator) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
