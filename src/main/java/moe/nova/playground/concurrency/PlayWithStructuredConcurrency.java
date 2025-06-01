package moe.nova.playground.concurrency;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.StructuredTaskScope;
import java.util.stream.Collectors;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class PlayWithStructuredConcurrency {

    record Weather(String weather) {
    }

    static void main(String[] args) {

        try (var scope = StructuredTaskScope.open()) {
            var future1 = scope.fork(readWeatherA());
            var future2 = scope.fork(readWeatherB());
            var future3 = scope.fork(readWeatherC());
            scope.join();

            IO.println("future1: " + future1.state());
            IO.println("future2: " + future2.state());
            IO.println("future3: " + future3.state());

            if (future1.state() == StructuredTaskScope.Subtask.State.SUCCESS) {
                IO.println("future1.get(): " + future1.get());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        printSeparateLine();

        try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.allSuccessfulOrThrow())) {
            List<Callable<Weather>> callables = List.of(readWeatherA(), readWeatherB(), readWeatherC());
            var subtasks = callables.stream().map(scope::fork).toList();
            scope.join();
            Map<StructuredTaskScope.Subtask.State, List<StructuredTaskScope.Subtask<Weather>>> map = subtasks.stream()
                    .collect(Collectors.groupingBy(StructuredTaskScope.Subtask::state, Collectors.toList()));
            map.forEach((key, val) -> System.out.print(key.name() + ": " + val.stream().map(PlayWithStructuredConcurrency::toString).collect(Collectors.joining(", ")) + "\n"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static String toString(StructuredTaskScope.Subtask<?> subtask) {
        if (subtask.state() == StructuredTaskScope.Subtask.State.SUCCESS) {
            return subtask.get().toString();
        } else if (subtask.state() == StructuredTaskScope.Subtask.State.FAILED) {
            return subtask.exception().getClass().toString();
        } else {
            return subtask.state().toString();
        }
    }

    private static Callable<Weather> readWeatherA() {
        return () -> {
            Thread.sleep(100);
            return new Weather("Sunny");
        };
    }

    private static Callable<Weather> readWeatherB() {
        return () -> {
            Thread.sleep(100);
            throw new IllegalStateException();
        };
    }

    private static Callable<Weather> readWeatherC() {
        return () -> {
            Thread.sleep(100);
            return new Weather("Rainy");
        };
    }
}
