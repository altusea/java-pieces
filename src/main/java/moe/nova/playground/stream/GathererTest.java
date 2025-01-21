package moe.nova.playground.stream;

import java.time.LocalDate;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Gatherer;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class GathererTest {

    record DateRecord(String type, LocalDate date) {
    }

    public static <T, K> Gatherer<T, ?, T> distinctBy(Function<? super T, ? extends K> distinctByExtractor) {
        Supplier<Set<K>> initializer = HashSet::new;
        Gatherer.Integrator<Set<K>, T, T> integrator = (state, element, downstream) -> {
            K key = distinctByExtractor.apply(element);
            if (!state.contains(key)) {
                state.add(key);
                downstream.push(element);
            }
            return true;
        };
        return Gatherer.ofSequential(initializer, integrator);
    }

    public static <T, K, C> Gatherer<T, ?, T> groupAndFindMax(Function<? super T, ? extends K> keyExtractor,
                                                              Function<? super T, ? extends C> maxByExtractor,
                                                              Comparator<? super C> maxByComparator) {

        Supplier<SequencedMap<K, T>> initializer = LinkedHashMap::new;
        Gatherer.Integrator<SequencedMap<K, T>, T, T> integrator = (state, element, _) -> {
            K key = keyExtractor.apply(element);
            if (state.containsKey(key)) {
                C oldC = maxByExtractor.apply(state.get(key));
                C newC = maxByExtractor.apply(element);
                if (maxByComparator.compare(oldC, newC) < 0) {
                    state.putLast(key, element);
                }
            } else {
                state.putLast(key, element);
            }
            return true;
        };
        BiConsumer<SequencedMap<K, T>, Gatherer.Downstream<? super T>> finisher = (state, downstream) -> state.values().forEach(downstream::push);
        return Gatherer.ofSequential(initializer, integrator, finisher);
    }

    public static void main(String[] args) {
        System.out.println("groupAndFindMax example: ");
        var listA = List.of(
                new DateRecord("B", LocalDate.of(2024, 6, 2)),
                new DateRecord("B", LocalDate.of(2024, 6, 3)), // desired
                new DateRecord("A", LocalDate.of(2024, 6, 4)),
                new DateRecord("A", LocalDate.of(2024, 6, 6)), // desired
                new DateRecord("A", LocalDate.of(2024, 6, 5)),
                new DateRecord("C", LocalDate.of(2024, 6, 4)), // desired
                new DateRecord("A", LocalDate.of(2024, 6, 3)),
                new DateRecord("D", LocalDate.of(2023, 6, 4)) // desired
        );
        var listB = listA.stream().gather(distinctBy(DateRecord::type)).toList();
        listB.forEach(System.out::println);

        System.out.println("groupAndFindMax example: ");
        var listC = listA.stream()
                .gather(groupAndFindMax(DateRecord::type, DateRecord::date, Comparator.naturalOrder()))
                .toList();
        listC.forEach(System.out::println);

        System.out.println("Fold example: ");
        Stream.of(1, 2, 3, 4, 5)
                .gather(Gatherers.fold(() -> 1, (a, b) -> a * b))
                .forEach(System.out::println);

        System.out.println("Map concurrent example:");
        Stream.of(1, 2, 3, 4, 5)
                .gather(Gatherers.mapConcurrent(4, a -> a * 2))
                .forEach(System.out::println);

        System.out.println("Scan example:");
        Stream.of(1, 2, 3, 4, 5)
                .gather(Gatherers.scan(() -> 1, (a, b) -> a * b))
                .forEach(System.out::println);

        System.out.println("Window fixed example:");
        Stream.of(1, 2, 3, 4, 5)
                .gather(Gatherers.windowFixed(2))
                .forEach(System.out::println);

        System.out.println("Window sliding example:");
        Stream.of(1, 2, 3, 4, 5)
                .gather(Gatherers.windowSliding(2))
                .forEach(System.out::println);
    }
}
