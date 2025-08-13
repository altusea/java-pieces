package moe.nova.util;

import org.apache.commons.collections4.ListValuedMap;
import org.apache.commons.collections4.MultiMapUtils;

import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class CustomCollectors {

    public static <T, K> Collector<T, ?, ListValuedMap<K, T>> toListValuedMap(Function<? super T, ? extends K> keyMapper) {
        return toListValuedMap(keyMapper, Function.identity());
    }

    public static <T, K, V> Collector<T, ?, ListValuedMap<K, V>> toListValuedMap(Function<? super T, ? extends K> keyMapper,
                                                                                 Function<? super T, ? extends V> valueMapper) {
        return Collector.of(
                MultiMapUtils::newListValuedHashMap,
                (map, element) -> map.put(keyMapper.apply(element), valueMapper.apply(element)),
                (left, right) -> {
                    left.putAll(right);
                    return left;
                },
                Collector.Characteristics.IDENTITY_FINISH
        );
    }

    record Triple<A, B, C>(A a, B b, C c) {

        public static <A, B, C> Triple<A, B, C> of(A a, B b, C c) {
            return new Triple<>(a, b, c);
        }
    }

    static void main() {
        var a1 = Triple.of(1, 2, 3);
        var b1 = Triple.of(4, 5, 6);
        var c1 = Triple.of(7, 8, 9);
        var a2 = Triple.of(1, 2, 3);
        var b2 = Triple.of(4, 5, 6);
        var c2 = Triple.of(7, 8, 9);
        var a3 = Triple.of(1, 2, 3);
        var b3 = Triple.of(4, 5, 6);
        var c3 = Triple.of(7, 8, 9);
        var z = Stream.of(a1, b1, c1, a2, b2, c2, a3, b3, c3).collect(toListValuedMap(Triple::a));
        IO.println(z);
        var z1 = Stream.of(a1, b1, c1, a2, b2, c2, a3, b3, c3).collect(toListValuedMap(Triple::a, Triple::b));
        IO.println(z1);
    }
}
