package ch29;


/**
 * program: java-learn->HouseCorp
 * description:
 * author: gerry
 * created: 2020-03-28 20:29
 **/
public class HouseCorp extends Corp {
    public HouseCorp(Product product) {
        super(product);
    }

    //房地产公司很High了，赚钱，计算利润
    public void makeMoney() {
        super.makeMoney();
        System.out.println("房地产公司赚大钱了...");
    }
}
