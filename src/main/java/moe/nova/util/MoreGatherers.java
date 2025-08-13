package moe.nova.util;

import java.util.Map;
import java.util.stream.Gatherer;

public class MoreGatherers {

    private MoreGatherers() {
        // No instances
    }

    public static <T> Gatherer<T, ?, Map.Entry<T, Long>> zipWithIndex() {
        return new ZipWithIndexGatherer<>();
    }
}
