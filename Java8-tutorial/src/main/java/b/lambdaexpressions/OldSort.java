package b.lambdaexpressions;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OldSort {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
//        Collections.sort(names, new Comparator<String>() {
//            @Override
//            public int compare(String a, String b) {
////                return b.compareTo(a);
//                return a.compareTo(b);
//            }
//        });

        names.sort(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });
        System.out.println(names);
    }
}
