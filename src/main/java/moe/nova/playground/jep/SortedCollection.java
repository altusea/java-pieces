package moe.nova.playground.jep;

import java.util.LinkedHashSet;
import java.util.SequencedSet;

public class SortedCollection {

    public static void main(String[] args) {
        SequencedSet<String> set = new LinkedHashSet<>();
        set.addLast("A");
        set.addLast("B");
        set.addLast("C");
        set.addLast("A");
        System.out.println(set);
        System.out.println(set.getFirst());
        System.out.println(set.getLast());

    }
}
