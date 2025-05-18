package moe.nova.playground;

import dev.failsafe.RateLimiter;

import java.time.Duration;

public class RateLimiterTest {

    public static void main(String[] args) {
        var limiter0 = RateLimiter.smoothBuilder(10, Duration.ofHours(1L)).build();
        IO.println("SmoothBuilder.tryAcquirePermit() returns " + limiter0.tryAcquirePermit());
        IO.println("SmoothBuilder.tryAcquirePermit() returns " + limiter0.tryAcquirePermit());

        var limiter1 = RateLimiter.burstyBuilder(10, Duration.ofHours(1L)).build();
        for (int i = 0; i < 10; i++) {
            IO.println(i + " " + limiter1.tryAcquirePermit());
        }
        IO.println(limiter1.tryAcquirePermit());
    }
}
