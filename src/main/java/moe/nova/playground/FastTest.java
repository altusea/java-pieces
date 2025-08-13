package moe.nova.playground;

import it.unimi.dsi.fastutil.io.FastByteArrayInputStream;
import it.unimi.dsi.fastutil.longs.*;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class FastTest {

    static void main() {
        LongList a = new LongArrayList();
        a.add(1L);
        a.add(2L);
        a.add(3L);
        IO.println(a.getFirst());

        printSeparateLine();
        LongStack longStack = new LongArrayList();
        longStack.push(3L);
        longStack.push(5L);
        longStack.push(7L);
        IO.println(longStack + "<");
        IO.println(longStack.popLong());
        IO.println(longStack.popLong());
        IO.println(longStack.popLong());
        IO.println(longStack.isEmpty());

        printSeparateLine();
        LongSet longSet = LongSets.fromTo(100L, 1000L);
        IO.println(longSet.getClass());
        IO.println(longSet.contains(500L));

        printSeparateLine();
        String s = "我能吞下玻璃而不伤害身体";
        try (FastByteArrayInputStream bais = new FastByteArrayInputStream(s.getBytes())) {
            while (bais.available() > 0) {
                IO.println(bais.read());
            }
        }
    }
}
