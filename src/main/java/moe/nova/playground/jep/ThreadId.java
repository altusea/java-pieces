package moe.nova.playground.jep;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadId {
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId = ThreadLocal.withInitial(nextId::getAndIncrement);

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }

    static void main() throws InterruptedException {
        // Run a task in a virtual thread
        Thread.startVirtualThread(() -> IO.println("Virtual Thread ID: " + get()));

        // Run the same task in a platform thread
        Thread t = new Thread(() -> IO.println("Platform Thread ID: " + get()));
        t.start();
        // wait
        Thread.sleep(Duration.ofSeconds(5L));
    }
}
