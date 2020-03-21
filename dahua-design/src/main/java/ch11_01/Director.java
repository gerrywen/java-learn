package ch11_01;

/**
 * program: java-learn->Director
 * description:
 * author: gerry
 * created: 2020-03-21 12:47
 **/
public class Director {
    // 创建具体建造者
    private Builder builder = new ConcreteBuilder();

    //构建不同的产品
    public Product getAProduct() {
        builder.setPart();
        // 设置不同的零件，产生不同的产品
        return builder.buildProduct();
    }
}
