package moe.nova.playground.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.StructuredTaskScope;

public class ScopedValueExample {

    private static final Logger log = LoggerFactory.getLogger(ScopedValueExample.class);

    private static final ScopedValue<String> scopedValue = ScopedValue.newInstance();

    static void main() {
        try (var scope = StructuredTaskScope.open()) {
            scope.fork(() -> {
                ScopedValue.where(scopedValue, "Scoped Value").run(
                        () -> IO.println(Thread.currentThread().threadId() + " - " + scopedValue.get()));
                return null;
            });
            scope.fork(() -> {
                ScopedValue.where(scopedValue, "Scoped Value").run(
                        () -> IO.println(Thread.currentThread().threadId() + " - " + scopedValue.get()));
                return null;
            });
            scope.join();
            new Thread(() -> IO.println(Thread.currentThread().threadId() + " - " + scopedValue.get())).start(); // throw NoSuchElementException
            scopedValue.get(); // throw NoSuchElementException
        } catch (InterruptedException e) {
            log.error("Interrupted", e);
        }
    }
}
