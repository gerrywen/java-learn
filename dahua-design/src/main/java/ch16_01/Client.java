package ch16_01;

/**
 * program: java-learn->Client
 * description:
 * author: gerry
 * created: 2020-03-21 20:47
 **/
public class Client {
    public static void main(String[] args) {
        //声明所有的处理节点
        ConcreteHandler1 handler1 = new ConcreteHandler1();
        ConcreteHandler2 handler2 = new ConcreteHandler2();
        ConcreteHandler3 handler3 = new ConcreteHandler3();
        //设置链中的阶段顺序1-->2-->3
        handler1.setNext(handler2);
        handler2.setNext(handler3);
        //提交请求，返回结果
        Request request = new Request();
        request.setLevel(1);
        Response response = handler1.handleMessage(request);
    }
}
