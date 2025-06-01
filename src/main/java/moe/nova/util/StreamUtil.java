package moe.nova.util;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class StreamUtil {

    private StreamUtil() {
    }

    @Unmodifiable
    @Contract(pure = true)
    public static <T, R> List<R> mapAsList(Collection<T> collection, Function<? super T, ? extends R> mapper) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyList();
        }
        return collection.stream().map(mapper).collect(Collectors.toUnmodifiableList());
    }

    @Contract(pure = true)
    public static <T, R> List<R> mapAsMutableList(Collection<T> collection, Function<? super T, ? extends R> mapper) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyList();
        }
        return collection.stream().map(mapper).collect(Collectors.toCollection(ArrayList::new));
    }

    @Unmodifiable
    @Contract(pure = true)
    public static <T, R> ImmutableList<R> mapAsImmutableList(Collection<T> collection, Function<? super T, ? extends R> mapper) {
        if (CollectionUtils.isEmpty(collection)) {
            return ImmutableList.of();
        }
        return collection.stream().map(mapper).collect(ImmutableList.toImmutableList());
    }

    @Unmodifiable
    @Contract(pure = true)
    public static <T, K, V> Map<K, V> toUnmodifiableMap(Collection<T> collection,
                                                        Function<? super T, ? extends K> keyMapper,
                                                        Function<? super T, ? extends V> valueMapper) {
        if (CollectionUtils.isEmpty(collection)) {
            return Map.of();
        }
        return collection.stream().collect(Collectors.toUnmodifiableMap(keyMapper, valueMapper));
    }

    @Unmodifiable
    @Contract(pure = true)
    public static <T, K, V> ImmutableMap<K, V> toImmutableMap(Collection<T> collection,
                                                              Function<? super T, ? extends K> keyMapper,
                                                              Function<? super T, ? extends V> valueMapper) {
        if (CollectionUtils.isEmpty(collection)) {
            return ImmutableMap.of();
        }
        return collection.stream().collect(ImmutableMap.toImmutableMap(keyMapper, valueMapper));
    }

    public static <T> Function<T, T> consumerToFunction(Consumer<T> consumer) {
        return t -> {
            consumer.accept(t);
            return t;
        };
    }

    public static <X, Y> Stream<Y> ofType(Collection<X> items, Class<Y> clazz) {
        return ofType(items.stream(), clazz);
    }

    public static <X, Y> Stream<Y> ofType(Stream<X> stream, Class<Y> clazz) {
        return stream
                .filter(clazz::isInstance)
                .map(clazz::cast);
    }

    public static <T> Collector<T, ?, T> single() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1) {
                        throw new IllegalStateException();
                    }
                    return list.getFirst();
                }
        );
    }

    static void main(String[] args) {
        List<Character> characters = Lists.newArrayList('a', 'b', 'c');
        List<CharSequence> charSequences = mapAsList(characters, e -> StringUtils.repeat(e, 3));
        IO.println(charSequences);
        List<String> strings = mapAsList(characters, e -> StringUtils.repeat(e, 3));
        IO.println(strings);

        List<String> a = List.of("a", "b", "c");
        var b = ofType(a, CharSequence.class).toList();
        b.forEach(System.out::println);
        printSeparateLine();
        var _ = a.stream().collect(single()).length();

    }
}
