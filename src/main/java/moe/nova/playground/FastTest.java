package moe.nova.playground;

import it.unimi.dsi.fastutil.io.FastByteArrayInputStream;
import it.unimi.dsi.fastutil.longs.*;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class FastTest {

    public static void main(String[] args) {
        LongList a = new LongArrayList();
        a.add(1L);
        a.add(2L);
        a.add(3L);
        System.out.println(a.getFirst());

        printSeparateLine();
        LongStack longStack = new LongArrayList();
        longStack.push(3L);
        longStack.push(5L);
        longStack.push(7L);
        System.out.println(longStack + "<");
        System.out.println(longStack.popLong());
        System.out.println(longStack.popLong());
        System.out.println(longStack.popLong());
        System.out.println(longStack.isEmpty());

        printSeparateLine();
        LongSet longSet = LongSets.fromTo(100L, 1000L);
        System.out.println(longSet.getClass());
        System.out.println(longSet.contains(500L));

        printSeparateLine();
        String s = "我能吞下玻璃而不伤害身体";
        try (FastByteArrayInputStream bais = new FastByteArrayInputStream(s.getBytes())) {
            while (bais.available() > 0) {
                System.out.println(bais.read());
            }
        }
    }
}
