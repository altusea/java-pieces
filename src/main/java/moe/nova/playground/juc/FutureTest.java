package moe.nova.playground.juc;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {

    // 模拟耗时计算
    private static int compute(int task) {
        try {
            Thread.sleep(1000); // 模拟耗时操作
        } catch (InterruptedException _) {
        }
        return task * 2;
    }

    public static void main(String[] args) {
        try (var threadPoolExecutor = Executors.newCachedThreadPool()) {
            long start = System.currentTimeMillis();

            // 创建任务列表
            List<Integer> taskList = Arrays.asList(1, 2, 3, 4, 5);
            List<Future<Integer>> futureList = new ArrayList<>();
            for (int task : taskList) {
                Future<Integer> future = threadPoolExecutor.submit(() -> compute(task));
                futureList.add(future);
            }
            for (Future<Integer> future : futureList) {
                future.get();
            }
            long end = System.currentTimeMillis();
            IO.println((end - start) + " ms!");
        } catch (Exception e) {
            throw ExceptionUtils.asRuntimeException(e);
        }
    }
}
