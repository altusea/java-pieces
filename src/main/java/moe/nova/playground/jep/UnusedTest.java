package moe.nova.playground.jep;

import com.google.common.collect.Lists;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class UnusedTest {

    static <T> int count(@NonNull Iterable<T> orders) {
        int total = 0;
        for (T _ : orders) // Unnamed variable
            total++;
        return total;
    }

    public static void main(String[] args) {
        List<String> a = Lists.newArrayList("a", "b", "c");
        System.out.println(count(a));
    }
}
