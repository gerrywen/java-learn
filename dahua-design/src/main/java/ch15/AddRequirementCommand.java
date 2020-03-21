package ch15;

/**
 * program: java-learn->AddRequirementCommand
 * description: 增加需求的命令
 * author: gerry
 * created: 2020-03-21 17:30
 **/
public class AddRequirementCommand extends Command {
    // 执行增加一项需求的命令
    @Override
    public void execute() {
        //找到需求组
        super.rg.find();
        //增加一份需求
        super.rg.add();
        //给出计划
        super.rg.plan();
    }
}
