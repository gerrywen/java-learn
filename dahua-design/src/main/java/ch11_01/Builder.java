package ch11_01;

/**
 * program: java-learn->Builder
 * description:
 * author: gerry
 * created: 2020-03-21 12:46
 **/
public abstract class Builder {
    //设置产品的不同部分，以获得不同的产品
    public abstract void setPart();

    //建造产品
    public abstract Product buildProduct();
}
