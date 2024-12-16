package moe.nova.playground.base;

public class ThreadLocalTest {

    public static void main(String[] args) {
        var threadLocal = ThreadLocal.withInitial(() -> "foo");
        threadLocal.set("bar");
        System.out.println(threadLocal.get());
        threadLocal.remove();
        System.out.println(threadLocal.get());
    }
}
