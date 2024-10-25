package moe.nova.playground.juc;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderTest {

    public static void main(String[] args) {
        final LongAdder longAdder = new LongAdder();
        try (var threadPoolExecutor = Executors.newCachedThreadPool()) {
            for (int i = 0; i < 1000; i++) {
                threadPoolExecutor.execute(longAdder::increment);
            }
            Thread.sleep(Duration.ofSeconds(1L));
            System.out.println(longAdder.sum());
        } catch (InterruptedException e) {
            throw ExceptionUtils.asRuntimeException(e);
        }
    }
}
