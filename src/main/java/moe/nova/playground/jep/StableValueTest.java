package moe.nova.playground.jep;

public class StableValueTest {

    private static final StableValue<String> stableValue = StableValue.of();

    static String get() {
        return stableValue.orElseSet(() -> "hello, world");
    }

    public static void main(String[] args) {

        System.out.println(get());
    }
}
