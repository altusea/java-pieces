package moe.nova.playground;

import dev.failsafe.Failsafe;
import dev.failsafe.RetryPolicy;
import org.apache.commons.lang3.NotImplementedException;

import java.time.Duration;
import java.time.LocalDateTime;

public class FailTest {

    public static void runWithException() {
        IO.println("exec time: " + LocalDateTime.now());
        throw new NotImplementedException("not implemented ...");
    }

    static void main() {
        RetryPolicy<Object> retryPolicy = RetryPolicy.builder()
                .handle(UnsupportedOperationException.class)
                .withDelay(Duration.ofSeconds(1))
                .withMaxAttempts(3)
                .build();

        try {
            Failsafe.with(retryPolicy).run(FailTest::runWithException);
        } catch (UnsupportedOperationException e) {
            IO.println(e.getMessage());
        }

        IO.println("====================================================");

        try {
            Failsafe.with(retryPolicy).run(context -> {
                IO.println("attempt count: " + context.getAttemptCount());
                runWithException();
            });
        } catch (UnsupportedOperationException e) {
            IO.println(e.getMessage());
        }
    }
}
