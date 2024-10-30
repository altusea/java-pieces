package moe.nova.playground.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateTest {

    public static void main(String[] args) {

        List<String> a = List.of("Aah", "Adder", "Ccc", "Aa", "Dylan");

        List<Predicate<String>> allPredicates = new ArrayList<>();
        allPredicates.add(str -> str.startsWith("A"));
        allPredicates.add(str -> str.contains("d"));
        allPredicates.add(str -> str.length() > 4);

        List<String> result = a.stream()
                .filter(allPredicates.stream().reduce(_ -> true, Predicate::and))
                .toList();

        System.out.println(result);
    }
}
