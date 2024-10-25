package moe.nova.playground;

import dev.failsafe.Failsafe;
import dev.failsafe.RetryPolicy;
import org.apache.commons.lang3.NotImplementedException;

import java.time.Duration;
import java.time.LocalDateTime;

public class FailTest {

    public static void runWithException() {
        System.out.println("exec time: " + LocalDateTime.now());
        throw new NotImplementedException("not implemented ...");
    }

    public static void main(String[] args) {
        RetryPolicy<Object> retryPolicy = RetryPolicy.builder()
                .handle(UnsupportedOperationException.class)
                .withDelay(Duration.ofSeconds(1))
                .withMaxAttempts(3)
                .build();

        try {
            Failsafe.with(retryPolicy).run(FailTest::runWithException);
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("====================================================");

        try {
            Failsafe.with(retryPolicy).run(context -> {
                System.out.println("attempt count: " + context.getAttemptCount());
                runWithException();
            });
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }
    }
}
