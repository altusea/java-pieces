package moe.nova.playground;

import com.ginsberg.gatherers4j.Gatherers4j;

import java.util.List;

public class GathererTest {
    public static void main(String[] args) {
        var a = List.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5);
        var b = a.stream()
                .gather(Gatherers4j.dedupeConsecutive())
                .toList();
        System.out.println(b);
    }
}
