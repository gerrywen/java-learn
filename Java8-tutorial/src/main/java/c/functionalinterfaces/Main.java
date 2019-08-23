package c.functionalinterfaces;

public class Main {
    public static void main(String[] args) {
//        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
//        Converter<String, Integer> converter = Integer::valueOf; // 过静态方法引用
//        Integer convert = converter.convert("123");
//        System.out.println(convert.getClass()); //class java.lang.Integer


//        Something something = new Something();
//        Converter<String, String> converter = something::startsWith;
//        String converted = converter.convert("Java");
//        System.out.println(converted);    // "J"


        PersonFactory<Person> personFactory  =  Person::new;
        Person person = personFactory.create("Peter", "Parker");

    }
}
