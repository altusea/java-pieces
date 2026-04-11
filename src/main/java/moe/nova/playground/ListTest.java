package moe.nova.playground;

import cn.hutool.v7.core.lang.tuple.Tuple;
import com.google.common.collect.Streams;

import java.util.List;

public class ListTest {

    static void main() {
        List<String> a = List.of("a", "b", "c");
        Streams.mapWithIndex(a.stream(), Tuple::of)
                .forEach(System.out::println);
    }
}
