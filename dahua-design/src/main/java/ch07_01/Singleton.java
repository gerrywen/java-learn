package ch07_01;

/**
 * program: java-learn->Singleton
 * description: 单例称为饿汉式单例
 *
 * TODO:该单利模式在多线程下可能出现多个对象
 * 可以在getSingleton方法前加了synchronized的单例称为懒汉式单例
 * author: gerry
 * created: 2020-03-19 22:57
 **/
public class Singleton {
    private static Singleton singleton = null;
    // 限制产生多个对象
    private Singleton() {

    }

    public static Singleton getSingleton() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
