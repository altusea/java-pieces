package moe.nova.playground.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SquareCalculator {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public Future<Integer> calculate(Integer input) {
        return executor.submit(() -> {
            Thread.sleep(1000);
            return input * input;
        });
    }

    static void main(String[] args) throws InterruptedException, ExecutionException {

        Future<Integer> future = new SquareCalculator().calculate(10);

        while (!future.isDone()) {
            IO.println("Calculating...");
            Thread.sleep(300);
        }

        Integer result = future.get();
        IO.println(result);
    }
}
