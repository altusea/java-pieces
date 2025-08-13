package moe.nova.util;

import org.apache.commons.collections4.ListValuedMap;
import org.apache.commons.collections4.MultiMapUtils;

import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class MoreCollectors {

    private MoreCollectors() {
        // No instances
    }

    public static <T, K> Collector<T, ?, ListValuedMap<K, T>> groupingBy(Function<? super T, ? extends K> classifier) {
        return Collector.of(
                MultiMapUtils::newListValuedHashMap,
                (map, element) -> map.put(classifier.apply(element), element),
                (left, right) -> {
                    left.putAll(right);
                    return left;
                }
        );
    }

    static void main() {
        ListValuedMap<Integer, String> a = Stream.of("a", "b", "c", "dd", "gg").collect(groupingBy(String::length));
        IO.println(a);
    }
}
