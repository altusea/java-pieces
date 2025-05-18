package moe.nova.playground.base;

public class ThreadLocalTest {

    public static void main(String[] args) {
        var threadLocal = ThreadLocal.withInitial(() -> "foo");
        threadLocal.set("bar");
        IO.println(threadLocal.get());
        threadLocal.remove();
        IO.println(threadLocal.get());
    }
}
