package moe.nova.playground.jep;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class StableValueTest {

    static void main(String[] args) {
        var supplier = StableValue.supplier(() -> "hello, world");
        IO.println(supplier.get());

        printSeparateLine();

        StableValue<String> stableValue2 = StableValue.of();
        IO.println(stableValue2.orElse("backup"));
        stableValue2.trySet("hello, world");
        IO.println(stableValue2.orElse("backup"));
    }
}
