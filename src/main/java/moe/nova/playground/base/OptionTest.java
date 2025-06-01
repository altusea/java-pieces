package moe.nova.playground.base;

import kala.collection.Seq;
import kala.collection.mutable.MutableArrayList;
import kala.control.Option;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionTest {

    public static <T> Optional<T> getFirst(List<T> list) {
        if (list == null || list.isEmpty()) return Optional.empty();
        return Optional.ofNullable(list.getFirst());
    }

    public static <T> Option<T> getFirst2(List<T> list) {
        if (list == null || list.isEmpty()) return Option.none();
        return Option.some(list.getFirst());
    }

    public static <T> Option<T> getFirst(Seq<T> seq) {
        if (seq == null || seq.isEmpty()) return Option.none();
        return Option.some(seq.getFirst());
    }

    static void main(String[] args) {
        var a = new ArrayList<String>();
        a.add(null);
        a.add("a");
        a.add("b");
        a.add("c");
        IO.println(getFirst(a));
        IO.println(getFirst2(a));

        var b = new MutableArrayList<>();
        b.append(null);
        b.append("a");
        b.append("b");
        b.append("c");
        IO.println(getFirst(b));
        IO.println(b.getFirstOption());
    }
}
