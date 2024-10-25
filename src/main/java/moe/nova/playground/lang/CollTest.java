package moe.nova.playground.lang;

import java.util.*;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class CollTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1); // 0
        list.add(2); // 1
        list.add(3); // 2
        list.add(4);
        list.add(5);
        list.add(6);
        list.remove(2);
        System.out.println(list);

        printSeparateLine("test SequencedMap:");
        SequencedMap<String, Integer> map = new LinkedHashMap<>();
        map.putLast("A", 1);
        map.putLast("B", 2);
        map.putLast("C", 3);
        map.putLast("A", 1);
        map.forEach((key, value) -> System.out.println(key + " : " + value));

        printSeparateLine("test SequencedSet:");
        SequencedSet<String> set = new LinkedHashSet<>();
        set.addLast("A");
        set.addLast("B");
        set.addLast("C");
        set.addLast("A");
        set.forEach(System.out::println);
    }
}
