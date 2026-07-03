package moe.nova.playground.helidon;

import moe.nova.util.LazyValue;

public class LazyTest {

    static void main() {
        LazyValue<String> lazyValue = LazyValue.create(() -> "Hello World");
        IO.println(lazyValue.isLoaded());
        lazyValue.get();
        IO.println(lazyValue.isLoaded());
    }
}
