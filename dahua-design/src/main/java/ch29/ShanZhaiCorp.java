package ch29;

/**
 * program: java-learn->ShanZhaiCorp
 * description:
 * author: gerry
 * created: 2020-03-28 20:30
 **/
public class ShanZhaiCorp extends Corp {
    //产什么产品，不知道，等被调用的才知道
    public ShanZhaiCorp(Product product) {
        super(product);
    }

    //狂赚钱
    public void makeMoney() {
        super.makeMoney();
        System.out.println("我赚钱呀...");
    }
}
