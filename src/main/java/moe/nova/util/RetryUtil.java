package moe.nova.util;

import dev.failsafe.Failsafe;
import dev.failsafe.RetryPolicy;
import dev.failsafe.RetryPolicyBuilder;
import dev.failsafe.function.CheckedPredicate;
import dev.failsafe.function.CheckedRunnable;
import dev.failsafe.function.CheckedSupplier;
import org.apache.commons.lang3.NotImplementedException;

import java.net.SocketTimeoutException;
import java.time.LocalDateTime;

public class RetryUtil {

    public static <R> R withTry(final CheckedSupplier<R> procedure,
                                final int maxAttempts,
                                final CheckedPredicate<? extends Throwable> failurePredicate) {

        RetryPolicyBuilder<R> retryPolicyBuilder = RetryPolicy.<R>builder()
                .withMaxAttempts(maxAttempts);
        if (failurePredicate != null) {
            retryPolicyBuilder.handleIf(failurePredicate);
        }
        RetryPolicy<R> retryPolicy = retryPolicyBuilder.build();
        return Failsafe.with(retryPolicy).get(procedure);
    }

    public static void withTry(final CheckedRunnable procedure,
                               final int maxAttempts,
                               final CheckedPredicate<? extends Throwable> failurePredicate) {
        RetryPolicyBuilder<Void> retryPolicyBuilder = RetryPolicy.<Void>builder()
                .withMaxAttempts(maxAttempts);
        if (failurePredicate != null) {
            retryPolicyBuilder.handleIf(failurePredicate);
        }
        RetryPolicy<Void> retryPolicy = retryPolicyBuilder.build();
        Failsafe.with(retryPolicy).run(procedure);
    }

    static void main() {
        withTry((CheckedRunnable) () -> {
            IO.println("exec time: " + LocalDateTime.now());
            throw new NotImplementedException("todo");
        }, 3, e -> e instanceof SocketTimeoutException);
    }
}
