package moe.nova.playground.jep;

import java.util.function.Supplier;

public class StableValueTest {

    private static final Supplier<String> stableValue = StableValue.supplier(
            () -> "hello, world"
    );

    public static void main(String[] args) {
        System.out.println(stableValue.get());
    }
}
