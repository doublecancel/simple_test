package ordering;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */
public class TestOrdering {


    public static void main(String[] args) {


        Ordering allEqual = Ordering.allEqual();
        Ordering arbitrary = Ordering.arbitrary();
        Ordering natural = Ordering.natural();
        Ordering usingToString = Ordering.usingToString();
        Ordering compound = Ordering.usingToString().compound((a, b) -> {
            return 0;
        });
        Ordering lexicographical = Ordering.usingToString().lexicographical();
        Ordering onResultOf = Ordering.usingToString().onResultOf((a) -> {
            return a + "a";
        });


        List<String> data = Lists.newArrayList("aaa", "ccc", "bbb", "ggg", "fff", "aaa");
//        data.sort(allEqual);

//        data.sort(arbitrary);
//        data.forEach(System.out::println);//随意排序

//        data.sort(natural);
//        data.forEach(System.out::println);//字典排序

//        data.sort(usingToString);
//        data.forEach(System.out::println);

        data.sort(usingToString);
        data.forEach(System.out::println);








    }



}
