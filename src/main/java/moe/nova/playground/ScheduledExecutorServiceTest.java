package moe.nova.playground;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTest {

    static void main() {
        var executor = Executors.newScheduledThreadPool(1);
        executor.scheduleWithFixedDelay(() -> IO.println("hello world"), 3, 3, TimeUnit.SECONDS);
    }
}
