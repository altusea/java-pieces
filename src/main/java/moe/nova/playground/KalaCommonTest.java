package moe.nova.playground;

import kala.collection.immutable.ImmutableArray;
import kala.collection.immutable.ImmutableMap;
import kala.collection.immutable.ImmutableVector;
import kala.collection.mutable.*;
import kala.control.Either;
import kala.control.Option;
import kala.control.Result;
import kala.control.Try;
import kala.value.LateInitValue;
import kala.value.LazyValue;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Function;
import java.util.stream.Collectors;

public class KalaCommonTest {

    public static void main(String[] args) {
        System.out.println("=====> Option");
        Option<String> optionA = Option.some(null);
        System.out.println(optionA);
        Option<String> optionB = Option.some("hello");
        System.out.println(optionB);
        Option<String> optionC = Option.none();
        System.out.println(optionC);

        System.out.println("=====> Either");
        Either<String, ?> either = Either.left("error msg");
        System.out.println(either.isLeft());
        System.out.println(either.isRight());
        System.out.println(either.toResult());

        System.out.println("\n=====> ImmutableArray");
        ImmutableArray<Integer> l = ImmutableArray.of(1, 2, 3, 4, 5);
        System.out.println(l.stream().reduce(Integer::sum));

        System.out.println("\n=====> MutableMap");
        MutableMap<String, Integer> lengthMap = new MutableHashMap<>();
        lengthMap.put("b", 2);
        lengthMap.put("c", null);
        lengthMap.putIfAbsent("b", 3);
        System.out.println(lengthMap.size());
        System.out.println(lengthMap.getOption("b"));
        System.out.println(lengthMap.getOption("c"));
        System.out.println(lengthMap.getOption("d"));
        System.out.println(lengthMap.getOrDefault("b", -1));
        System.out.println(lengthMap.getOrElse("e", () -> 100));

        System.out.println("\n=====> ImmutableMap");
        var stringList = ImmutableVector.of("hello", "world", "i", "wanna", "say");
        var immutableMap = stringList.stream()
                .collect(ImmutableMap.collector(Function.identity(), String::length));
        immutableMap.forEach((k, v) -> System.out.println(k + " : " + v));

        System.out.println("\n=====> MutableDeque");
        MutableDeque<String> deque = new MutableArrayDeque<>();
        deque.append("aaa");
        deque.append("bbb");
        deque.prepend("ccc");
        System.out.println(deque);

        System.out.println("\n=====> MutableList");
        MutableList<Integer> mutableList = MutableList.of(1, 2, 3);
        mutableList.append(4);
        mutableList.append(5);
        mutableList.append(6);
        System.out.println(mutableList.stream().map(String::valueOf).collect(Collectors.joining(", ")));

        System.out.println("\n=====> Result");
        Result<Integer, ?> result1 = Result.ok(100);
        System.out.println(result1);
        Result<?, String> result2 = Result.err("500");
        System.out.println(result2);

        System.out.println("\n=====> Try");
        Try<String> stringTry = Try.of(() -> "hello");
        System.out.println(stringTry.isSuccess());
        Try<String> stringTry1 = Try.of(() -> {
            throw new NotImplementedException();
        });
        System.out.println(stringTry1.isSuccess());

        System.out.println("\n=====> Immutable Vectors");
        ImmutableVector<String> immutableVector = ImmutableVector.fill(5, "String");
        System.out.println(immutableVector.size());

        System.out.println("\n=====> Lazy");
        LazyValue<String> lazyValue = LazyValue.of(() -> System.getProperty("os.name").toLowerCase());
        System.out.println(lazyValue.isReady());
        System.out.println(lazyValue.get());
        System.out.println(lazyValue.get().contains("windows"));
        System.out.println(lazyValue.isReady());

        LateInitValue<String> lateInitValue = new LateInitValue<>();
        System.out.println(lateInitValue.isInitialized());
        lateInitValue.initialize("sss");
        System.out.println(lateInitValue.isInitialized());
    }
}
