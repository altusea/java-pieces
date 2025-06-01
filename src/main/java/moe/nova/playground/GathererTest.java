package moe.nova.playground;

import com.ginsberg.gatherers4j.Gatherers4j;
import com.pivovarit.gatherers.MoreGatherers;

import java.util.List;
import java.util.Map;

public class GathererTest {
    static void main(String[] args) {
        var a = List.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5);
        var b = a.stream()
                .gather(Gatherers4j.dedupeConsecutive())
                .toList();
        IO.println(b);

        var c = a.stream()
                .gather(MoreGatherers.zipWithIndex())
                .gather(MoreGatherers.distinctByKeepLast(Map.Entry::getKey))
                .toList();
        IO.println(c);
    }
}
