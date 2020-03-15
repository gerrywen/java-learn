package ch01_01;

/**
 * program: java-learn->OperationFactory
 * description:
 * author: gerry
 * created: 2020-03-15 20:12
 **/
public interface OperationFactory {

    Operation createOperation(String operate);

    // 实现类
    static OperationFactoryImpl impl = new OperationFactoryImpl();

    // 获取工厂实例:
    static Operation createFactory(String operate) {
        return impl.createOperation(operate);
    }
}
