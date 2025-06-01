package moe.nova.playground;

import kala.collection.Seq;
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

    static void main(String[] args) {
        IO.println("Hello, Kala.");

        IO.println("\n=====> Seq");
        var a = Seq.of(1, 2, 3, 4, 5);
        IO.println(a.slice(0, ~2));
        IO.println(a.slice(~3, ~0));

        IO.println("\n=====> Option");
        Option<String> optionA = Option.some(null);
        IO.println(optionA);
        Option<String> optionB = Option.some("hello");
        IO.println(optionB);
        Option<String> optionC = Option.none();
        IO.println(optionC);

        IO.println("\n=====> Either");
        Either<String, ?> either = Either.left("error msg");
        IO.println(either.isLeft()); // should be "true"
        IO.println(either.toResult());
        var either2 = either.swap();
        IO.println(either2.isRight()); // should be "true"

        IO.println("\n=====> ImmutableArray");
        ImmutableArray<Integer> l = ImmutableArray.of(1, 2, 3, 4, 5);
        IO.println(l.stream().reduce(Integer::sum));

        IO.println("\n=====> MutableMap");
        MutableMap<String, Integer> lengthMap = new MutableHashMap<>();
        lengthMap.put("b", 2);
        lengthMap.put("c", null);
        lengthMap.putIfAbsent("b", 3);
        IO.println(lengthMap.size());
        IO.println(lengthMap.getOption("b"));
        IO.println(lengthMap.getOption("c"));
        IO.println(lengthMap.getOption("d"));
        IO.println(lengthMap.getOrDefault("b", -1));
        IO.println(lengthMap.getOrElse("e", () -> 100));

        IO.println("\n=====> ImmutableMap");
        var stringList = ImmutableVector.of("hello", "world", "i", "wanna", "say");
        var immutableMap = stringList.stream()
                .collect(ImmutableMap.collector(Function.identity(), String::length));
        immutableMap.forEach((k, v) -> IO.println(k + " : " + v));

        IO.println("\n=====> MutableDeque");
        MutableDeque<String> deque = new MutableArrayDeque<>();
        deque.append("aaa");
        deque.append("bbb");
        deque.prepend("ccc");
        IO.println(deque);

        IO.println("\n=====> MutableList");
        MutableList<Integer> mutableList = MutableList.of(1, 2, 3);
        mutableList.append(4);
        mutableList.append(5);
        mutableList.append(6);
        IO.println(mutableList.stream().map(String::valueOf).collect(Collectors.joining(", ")));

        IO.println("\n=====> Result");
        Result<Integer, ?> result1 = Result.ok(100);
        IO.println(result1);
        Result<?, String> result2 = Result.err("500");
        IO.println(result2);

        IO.println("\n=====> Try");
        Try<String> stringTry = Try.of(() -> "hello");
        IO.println(stringTry.isSuccess());
        Try<String> stringTry1 = Try.of(() -> {
            throw new NotImplementedException();
        });
        IO.println(stringTry1.isSuccess());

        IO.println("\n=====> Immutable Vectors");
        ImmutableVector<String> immutableVector = ImmutableVector.fill(5, "String");
        IO.println(immutableVector.size());

        IO.println("\n=====> Lazy");
        LazyValue<String> lazyValue = LazyValue.of(() -> System.getProperty("os.name").toLowerCase());
        IO.println(lazyValue.isReady());
        IO.println(lazyValue.get());
        IO.println(lazyValue.get().contains("windows"));
        IO.println(lazyValue.isReady());

        LateInitValue<String> lateInitValue = new LateInitValue<>();
        IO.println(lateInitValue.isInitialized());
        lateInitValue.initialize("sss");
        IO.println(lateInitValue.isInitialized());
    }
}
