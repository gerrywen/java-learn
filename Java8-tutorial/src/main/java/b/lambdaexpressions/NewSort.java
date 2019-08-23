package b.lambdaexpressions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NewSort {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        names.sort((String a, String b) -> b.compareTo(a));
        System.out.println(names);
    }
}
