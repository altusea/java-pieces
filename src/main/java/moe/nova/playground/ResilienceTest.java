package moe.nova.playground;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

public class ResilienceTest {

    static void main(String[] args) {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(2)
                .waitDuration(Duration.ofMillis(100))
                .retryExceptions(IOException.class, TimeoutException.class)
                .build();
        String str = Retry.of("test", config).executeSupplier(() -> "hello");
        IO.println(str);
    }
}
