package moe.nova.playground.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {

    public static class Balance {

        private int amount;

        public Balance(int amount) {
            this.amount = amount;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }

    static void main(String[] args) {
        StampedLock lock = new StampedLock();
        Balance b = new Balance(10000);
        Runnable w = () -> {
            long stamp = lock.writeLock();
            b.setAmount(b.getAmount() + 1000);
            IO.println("Write: " + b.getAmount());
            lock.unlockWrite(stamp);
        };
        Runnable r = () -> {
            long stamp = lock.tryOptimisticRead();
            if (!lock.validate(stamp)) {
                stamp = lock.readLock();
                try {
                    IO.println("Read: " + b.getAmount());
                } finally {
                    lock.unlockRead(stamp);
                }
            } else {
                IO.println("Optimistic read fails");
            }
        };

        try (ExecutorService executor = Executors.newFixedThreadPool(10)) {
            for (int i = 0; i < 50; i++) {
                executor.submit(w);
                executor.submit(r);
            }
        }
    }
}
