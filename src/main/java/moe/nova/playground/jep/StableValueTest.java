package moe.nova.playground.jep;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class StableValueTest {

    public static void main(String[] args) {
        var supplier = StableValue.supplier(() -> "hello, world");
        System.out.println(supplier.get());

        printSeparateLine();

        StableValue<String> stableValue2 = StableValue.of();
        System.out.println(stableValue2.orElse("backup"));
        stableValue2.trySet("hello, world");
        System.out.println(stableValue2.orElse("backup"));
    }
}
