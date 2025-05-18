package moe.nova.playground.base;

import com.google.common.collect.Lists;

import java.util.*;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class CollTest {

    public static void main(String[] args) {
        var a = Lists.newArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        a.removeIf(e -> Integer.parseInt(e) % 2 == 0);
        IO.println(a);

        printSeparateLine();
        List<Integer> list = new ArrayList<>();
        list.add(1); // 0
        list.add(2); // 1
        list.add(3); // 2
        list.add(4);
        list.add(5);
        list.add(6);
        list.remove(2);
        IO.println(list);

        printSeparateLine("test SequencedMap:");
        SequencedMap<String, Integer> map = new LinkedHashMap<>();
        map.putLast("A", 1);
        map.putLast("B", 2);
        map.putLast("C", 3);
        map.putLast("A", 1);
        map.forEach((key, value) -> IO.println(key + " : " + value));

        printSeparateLine("test SequencedSet:");
        SequencedSet<String> set = new LinkedHashSet<>();
        set.addLast("A");
        set.addLast("B");
        set.addLast("C");
        set.addLast("A");
        set.forEach(System.out::println);
    }
}
