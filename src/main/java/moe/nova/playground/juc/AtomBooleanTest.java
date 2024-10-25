package moe.nova.playground.juc;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class AtomBooleanTest {

    public static void main(String[] args) throws InterruptedException {
        final AtomicBoolean atomBoolean = new AtomicBoolean(false);

        try (ExecutorService executor = Executors.newCachedThreadPool()) {
            for (int i = 0; i < 10; i++) {
                executor.execute(() -> {
                    if (atomBoolean.compareAndSet(false, true)) {
                        System.out.println(Thread.currentThread().getName() + " set atomBoolean to true ...");
                    } else {
                        System.out.println(Thread.currentThread().getName() + " fail ...");
                    }
                });
            }
            Thread.sleep(Duration.ofSeconds(1L));
        }
    }
}
