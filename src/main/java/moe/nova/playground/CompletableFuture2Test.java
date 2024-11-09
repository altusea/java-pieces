package moe.nova.playground;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class CompletableFuture2Test {

    public static void main(String[] args) {
        try {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(Duration.ofSeconds(1L));
                } catch (Exception ignore) {
                }
                return "Hello";
            });
            future.whenComplete(
                    (result, _) -> System.out.println("Future completed with result=" + result + "."));

            Thread.sleep(Duration.ofSeconds(5));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
