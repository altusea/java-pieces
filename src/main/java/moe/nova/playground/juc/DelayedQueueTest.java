package moe.nova.playground.juc;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedQueueTest {

    private static final Logger log = LoggerFactory.getLogger(DelayedQueueTest.class);

    record DelayedEvent(long startTime, String msg) implements Delayed {

        public long getDelay(TimeUnit unit) {
            long diff = startTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        public int compareTo(@NonNull Delayed o) {
            return (int) (this.startTime - ((DelayedEvent) o).startTime);
        }

    }

    static void main(String[] args) throws InterruptedException {
        final var delayQueue = new DelayQueue<DelayedEvent>();
        final long timeFirst = System.currentTimeMillis() + 10000;
        delayQueue.offer(new DelayedEvent(timeFirst, "1"));
        log.info("Done");
        log.info(delayQueue.take().msg());
    }
}
