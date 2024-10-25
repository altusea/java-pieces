package moe.nova.playground.juc;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CompletableFutureTest {

    public static void main(String[] args) {

        try (var threadPoolExecutor = Executors.newVirtualThreadPerTaskExecutor()) {

            long start = System.currentTimeMillis();

            // 创建任务列表
            List<Integer> taskList = Arrays.asList(1, 2, 3, 4, 5);

            // 并行执行任务，并收集结果
            List<CompletableFuture<Integer>> futures = taskList.stream()
                    .map(task -> CompletableFuture.supplyAsync(() -> compute(task), threadPoolExecutor))
                    .toList();

            // 将所有CompletableFuture组合成一个CompletableFuture，等待所有任务完成
            CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

            // 在所有任务完成后，处理每个任务的结果
            CompletableFuture<List<Integer>> resultFuture = allFutures.thenApply(_ ->
                    futures.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList())
            );

            // 等待结果，并输出
            List<Integer> results = resultFuture.join();
            long end = System.currentTimeMillis();
            System.out.println("Results: " + results);
            System.out.println((end - start) + "ms");
        }
    }

    // 模拟耗时计算
    private static int compute(int task) {
        try {
            Thread.sleep(1000); // 模拟耗时操作
        } catch (InterruptedException _) {
        }
        return task * 2;
    }
}
