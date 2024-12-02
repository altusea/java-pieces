package moe.nova.playground.concurrency;

import java.util.concurrent.StructuredTaskScope;

public class ScopedValueExample {
    private static final ScopedValue<String> scopedValue = ScopedValue.newInstance();

    public static void main(String[] args) {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            scope.fork(() -> {
                ScopedValue.where(scopedValue, "Scoped Value").run(() -> {
                    System.out.println(Thread.currentThread().getName() + " - " + scopedValue.get());
                });
                return null;
            });
            scope.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
