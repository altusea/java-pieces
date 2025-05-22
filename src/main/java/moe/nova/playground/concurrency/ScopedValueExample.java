package moe.nova.playground.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.StructuredTaskScope;

public class ScopedValueExample {

    private static final Logger log = LoggerFactory.getLogger(ScopedValueExample.class);

    private static final ScopedValue<String> scopedValue = ScopedValue.newInstance();

    public static void main(String[] args) {
        try (var scope = StructuredTaskScope.open()) {
            scope.fork(() -> {
                ScopedValue.where(scopedValue, "Scoped Value").run(
                        () -> IO.println(Thread.currentThread().getName() + " - " + scopedValue.get()));
                return null;
            });
            scope.join();
        } catch (InterruptedException e) {
            log.error("Interrupted", e);
        }
    }
}
