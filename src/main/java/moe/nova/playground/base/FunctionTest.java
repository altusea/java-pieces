package moe.nova.playground.base;

import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;
import java.util.function.Consumer;
import java.util.stream.Gatherers;

public class FunctionTest {

    public static void main(String[] args) {
        Consumer<SequencedCollection<String>> consumer = e -> e.addLast("tail");
        var stringList = new ArrayList<String>();
        stringList.addFirst("head");
        consumer.accept(stringList);
        IO.println(stringList);

        var c = List.of("head", "xx1", "xx2", "xx3", "xx4", "xx5", "tail");
        c.stream()
                .gather(Gatherers.windowSliding(3))
                .forEach(System.out::println);
    }
}
