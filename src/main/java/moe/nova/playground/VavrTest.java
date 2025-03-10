package moe.nova.playground;

import io.vavr.Function0;
import io.vavr.Function2;
import io.vavr.collection.Stream;
import io.vavr.control.Option;

import java.util.function.Supplier;

public class VavrTest {

    public static void main(String[] args) {
        Supplier<String> supplier = () -> "hello";
        var stream = Stream.continually(supplier);
        System.out.println(stream.zipWithIndex().take(3).toJavaList());

        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;
        Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);
        Option<Integer> i1 = safeDivide.apply(1, 0); // ==> None
        System.out.println(i1);
        Option<Integer> i2 = safeDivide.apply(4, 2); // ==> Some(2)
        System.out.println(i2);

        Function0<Double> hashCache = Function0.of(Math::random).memoized();

        double randomValue1 = hashCache.apply();
        double randomValue2 = hashCache.apply();
        System.out.printf("%.2f %.2f%n", randomValue1, randomValue2);
    }
}
