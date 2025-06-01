package moe.nova.playground.helidon;

import io.helidon.common.LazyValue;

public class LazyTest {

    static void main(String[] args) {
        LazyValue<String> lazyValue = LazyValue.create(() -> "Hello World");
        IO.println(lazyValue.isLoaded());
        lazyValue.get();
        IO.println(lazyValue.isLoaded());
    }
}
