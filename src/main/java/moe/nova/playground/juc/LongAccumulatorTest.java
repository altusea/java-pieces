package moe.nova.playground.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;

public class LongAccumulatorTest {

    public static void main(String[] args) {
        LongAccumulator balance = new LongAccumulator(Long::sum, 10000L);
        Runnable w = () -> balance.accumulate(1000L);
        try (ExecutorService executor = Executors.newFixedThreadPool(50)) {
            for (int i = 0; i < 50; i++) {
                executor.submit(w);
            }

            executor.shutdown();
            if (executor.awaitTermination(1000L, TimeUnit.MILLISECONDS))
                IO.println("Balance: " + balance.get());
            assert balance.get() == 60000L;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
