package moe.nova.playground;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

public class GuavaTest {

    static void main(String[] args) {
        Joiner joiner = Joiner.on("; ").skipNulls();
        IO.println(joiner.join("Harry", null, "Ron", "Hermione"));

        IO.println(Splitter.on(',').trimResults().omitEmptyStrings()
                .split("foo,bar,,   qux"));

        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10)); // {[1, 10]}
        IO.println(rangeSet);
        rangeSet.add(Range.closedOpen(11, 15)); // disconnected range: {[1, 10], [11, 15)}
        IO.println(rangeSet);
        rangeSet.add(Range.closedOpen(15, 20)); // connected range; {[1, 10], [11, 20)}
        IO.println(rangeSet);
        rangeSet.add(Range.openClosed(0, 0)); // empty range; {[1, 10], [11, 20)}
        IO.println(rangeSet);
        rangeSet.remove(Range.open(5, 10)); // splits [1, 10]; {[1, 5], [10, 10], [11, 20)}
        IO.println(rangeSet);
    }
}
