package moe.nova.playground.jep;

import java.util.LinkedHashSet;
import java.util.SequencedSet;

public class SortedCollection {

    static void main() {
        SequencedSet<String> set = new LinkedHashSet<>();
        set.addLast("A");
        set.addLast("B");
        set.addLast("C");
        set.addLast("A");
        IO.println(set);
        IO.println(set.getFirst());
        IO.println(set.getLast());

    }
}
