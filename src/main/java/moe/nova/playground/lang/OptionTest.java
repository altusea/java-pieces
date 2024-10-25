package moe.nova.playground.lang;

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

    public static void main(String[] args) {
        var a = new ArrayList<String>();
        a.add(null);
        a.add("a");
        a.add("b");
        a.add("c");
        System.out.println(getFirst(a));
        System.out.println(getFirst2(a));

        var b = new MutableArrayList<>();
        b.append(null);
        b.append("a");
        b.append("b");
        b.append("c");
        System.out.println(getFirst(b));
        System.out.println(b.getFirstOption());
    }
}
