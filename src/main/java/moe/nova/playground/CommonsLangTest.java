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

    public static void main(String[] args) throws InterruptedException {
        MutableInt mutableInt = new MutableInt(100);
        mutableInt.increment();
        System.out.println(mutableInt.getAndAdd(5)); // should be 101
        System.out.println(mutableInt.getValue()); // should be 106

        printSeparateLine();
        Consumer<String> consumer = Consumers.nop();
        consumer.accept("hello");
        System.out.println("here something ha");
        printSeparateLine();
        var l = new ArrayList<String>();
        IntRange.closedOpen(0, 10).forEach(_ -> l.add(RandomStringUtils.insecure().nextNumeric(6)));
        System.out.println(StringUtils.join(l, ", "));

        var stopWatch = StopWatch.createStarted();
        Thread.sleep(Duration.ofSeconds(5));
        stopWatch.stop();
        System.out.println(stopWatch.formatTime());

        printSeparateLine();
        int[] a = new int[]{1, 2, 3};
        var i = ArrayUtils.indexOf(a, 2);
        var b = new long[10];
        ArrayFill.fill(b, 1000L);
    }
}
