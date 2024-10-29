package moe.nova.playground.helidon;

import io.helidon.common.LazyValue;

public class LazyTest {

    public static void main(String[] args) {
        LazyValue<String> lazyValue = LazyValue.create(() -> "Hello World");
        System.out.println(lazyValue.isLoaded());
        lazyValue.get();
        System.out.println(lazyValue.isLoaded());
    }
}
