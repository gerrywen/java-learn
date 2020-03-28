package ch29;

/**
 * program: java-learn->Product
 * description:  抽象产品类
 * author: gerry
 * created: 2020-03-28 20:24
 **/
public abstract class Product {
    //甭管是什么产品它总要能被生产出来
    public abstract void beProducted();

    //生产出来的东西，一定要销售出去，否则亏本
    public abstract void beSelled();
}
