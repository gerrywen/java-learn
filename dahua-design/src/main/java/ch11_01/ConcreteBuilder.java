package ch11_01;

/**
 * program: java-learn->ConcreteProduct
 * description: 具体建造者
 * author: gerry
 * created: 2020-03-21 12:47
 **/
public class ConcreteBuilder extends Builder {
    private Product product = new Product();

    @Override
    public void setPart() {
        System.out.println("产品类内的逻辑处理");
    }

    @Override
    public Product buildProduct() {
        return this.product;
    }
}
