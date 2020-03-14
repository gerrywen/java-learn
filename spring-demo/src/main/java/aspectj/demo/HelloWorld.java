package aspectj.demo;

/**
 * program: java-learn->HelloWorld
 * description:
 * author: gerry
 * created: 2020-03-06 19:38
 **/
public class HelloWorld {
    public void sayHello() {
        System.out.println("Hello Aspectj!");
    }

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.sayHello();
    }
}
