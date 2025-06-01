package moe.nova.playground.concurrency;

import java.util.concurrent.Executors;

public class VirtualThreadLocalTest {

    private static final ThreadLocal<Long> localVar = ThreadLocal.withInitial(
            () -> Thread.currentThread().threadId()
    );

    static void main(String[] args) {
        try (var e = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 100; i++) {
                e.execute(() -> IO.println(Thread.currentThread().threadId() + " get: " + localVar.get()));
            }
        }
    }
}
