package moe.nova.playground;

import kala.range.primitive.IntRange;
import org.apache.commons.lang3.ArrayFill;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.function.Consumers;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.time.StopWatch;

import java.time.Duration;
import java.util.ArrayList;
import java.util.function.Consumer;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class CommonsLangTest {

    static void main() throws InterruptedException {
        MutableInt mutableInt = new MutableInt(100);
        mutableInt.increment();
        IO.println(mutableInt.getAndAdd(5)); // should be 101
        IO.println(mutableInt.get()); // should be 106

        printSeparateLine();
        Consumer<String> consumer = Consumers.nop();
        consumer.accept("hello");
        IO.println("here something ha");
        printSeparateLine();
        var l = new ArrayList<String>();
        IntRange.closedOpen(0, 10).forEach(_ -> l.add(RandomStringUtils.insecure().nextNumeric(6)));
        IO.println(StringUtils.join(l, ", "));

        var stopWatch = StopWatch.createStarted();
        Thread.sleep(Duration.ofSeconds(3));
        stopWatch.suspend();
        Thread.sleep(Duration.ofSeconds(2));
        stopWatch.resume();
        Thread.sleep(Duration.ofSeconds(1));
        stopWatch.stop();
        IO.println(stopWatch.formatTime()); // should be 4 seconds

        printSeparateLine();
        int[] a = new int[]{1, 2, 3};
        var i = ArrayUtils.indexOf(a, 2);
        var b = new long[10];
        ArrayFill.fill(b, 1000L);
    }
}
