package ch06_01;

/**
 * program: java-learn->Person
 * description: 人
 * author: gerry
 * created: 2020-03-17 21:26
 **/
public class Person {
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("装扮的" + name);
    }
}
