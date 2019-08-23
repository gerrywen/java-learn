package e.builtinfunctionalinterfaces;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Predicate<String> predicate = s -> s.length() > 0;
        boolean foo = predicate.test("foo");
        boolean foo1 = predicate.negate().test("foo");
        System.out.println(foo);
        System.out.println(foo1);


        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        System.out.println(nonNull);
        System.out.println(isNull);


        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
        System.out.println(isEmpty);
        System.out.println(isNotEmpty);

    }
}
