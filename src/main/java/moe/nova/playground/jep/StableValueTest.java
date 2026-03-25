package moe.nova.playground.jep;

import moe.nova.util.Lazy;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class StableValueTest {

    static void main() {
        var supplier = LazyConstant.of(() -> "hello, world");
        IO.println(supplier.get());

        printSeparateLine();

        LazyConstant<String> stableValue2 = LazyConstant.of(() -> "zhu");
        IO.println(stableValue2.orElse("backup"));
        stableValue2.get();
        IO.println(stableValue2.orElse("backup"));

        var e = Lazy.of(() -> "string");
        System.out.println(e.getClass());
        System.out.println(e.get());
    }
}
