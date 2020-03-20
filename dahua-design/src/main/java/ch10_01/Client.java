package ch10_01;

/**
 * program: java-learn->Client
 * description:
 * author: gerry
 * created: 2020-03-20 22:41
 **/
public class Client {
    public static void main(String[] args) {
        AbstractClass class1 = new ConcreteClass1();
        AbstractClass class2 = new ConcreteClass2();
        // 调用模板方法
        class1.templateMethod();
        class2.templateMethod();
    }
}
