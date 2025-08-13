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

    static void main() {
        List<String> a = Lists.newArrayList("a", "b", "c");
        IO.println(count(a));
    }
}
