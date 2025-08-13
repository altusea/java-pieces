package moe.nova.playground.base;

public class ThreadLocalTest {

    static void main() {
        var threadLocal = ThreadLocal.withInitial(() -> "foo");
        threadLocal.set("bar");
        IO.println(threadLocal.get());
        threadLocal.remove();
        IO.println(threadLocal.get());
    }
}
