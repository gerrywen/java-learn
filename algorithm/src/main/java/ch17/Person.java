package ch17;

/**
 * @author wenguoli
 * @description:
 * @date 2020/2/15 0015 18:25
 */
public class Person implements Comparable<Person>{
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(int age) {
        this.age = age;
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Person o) {
        return age - o.age;
    }

    @Override
    public String toString() {
        return age + "_" + name;
    }
}
