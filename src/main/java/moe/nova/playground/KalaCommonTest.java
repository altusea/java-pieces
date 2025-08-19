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

    static void main() {
        IO.println("[Line 022] Hello, Kala.");

        IO.println("[Line 024] =====> Seq");
        var a = Seq.of(1, 2, 3, 4, 5);
        IO.println("[Line 026] " + a.slice(0, ~2));
        IO.println("[Line 027] " + a.slice(~3, ~0));

        IO.println("[Line 029] =====> Option");
        Option<String> optionA = Option.some(null);
        IO.println("[Line 031] " + optionA);
        Option<String> optionB = Option.some("hello");
        IO.println("[Line 033] " + optionB);
        Option<String> optionC = Option.none();
        IO.println("[Line 035] " + optionC);

        IO.println("[Line 037] =====> Either");
        Either<String, ?> either = Either.left("error msg");
        IO.println("[Line 039] " + either.isLeft()); // should be "true"
        IO.println("[Line 040] " + either.toResult());
        var either2 = either.swap();
        IO.println("[Line 042] " + either2.isRight()); // should be "true"

        IO.println("[Line 044] =====> ImmutableArray");
        ImmutableArray<Integer> l = ImmutableArray.of(1, 2, 3, 4, 5);
        IO.println("[Line 046] " + l.stream().reduce(Integer::sum));

        IO.println("[Line 048] =====> MutableMap");
        MutableMap<String, Integer> lengthMap = new MutableHashMap<>();
        lengthMap.put("b", 2);
        lengthMap.put("c", null);
        lengthMap.putIfAbsent("b", 3);
        IO.println("[Line 053] " + lengthMap.size());
        IO.println("[Line 054] " + lengthMap.getOption("b"));
        IO.println("[Line 055] " + lengthMap.getOption("c"));
        IO.println("[Line 056] " + lengthMap.getOption("d"));
        IO.println("[Line 057] " + lengthMap.getOrDefault("b", -1));
        IO.println("[Line 058] " + lengthMap.getOrElse("e", () -> 100));

        IO.println("[Line 060] =====> ImmutableMap");
        var stringList = ImmutableVector.of("hello", "world", "i", "wanna", "say");
        var immutableMap = stringList.stream()
                .collect(ImmutableMap.collector(Function.identity(), String::length));
        immutableMap.forEach((k, v) -> IO.println("[Line 058] " + k + " : " + v));

        IO.println("[Line 066] =====> MutableDeque");
        MutableDeque<String> deque = new MutableArrayDeque<>();
        deque.append("aaa");
        deque.append("bbb");
        deque.prepend("ccc");
        IO.println("[Line 071] " + deque);

        IO.println("[Line 073] =====> MutableList");
        MutableList<Integer> mutableList = MutableList.of(1, 2, 3);
        mutableList.append(4);
        mutableList.append(5);
        mutableList.append(6);
        IO.println("[Line 078] " + mutableList.stream().map(String::valueOf).collect(Collectors.joining(", ")));

        IO.println("[Line 080] =====> Result");
        Result<Integer, ?> result1 = Result.ok(100);
        IO.println("[Line 082] " + result1);
        Result<?, String> result2 = Result.err("500");
        IO.println("[Line 084] " + result2);

        IO.println("[Line 086] =====> Try");
        Try<String> stringTry = Try.of(() -> "hello");
        IO.println("[Line 088] " + stringTry.isSuccess());
        Try<String> stringTry1 = Try.of(() -> {
            throw new NotImplementedException();
        });
        IO.println("[Line 092] " + stringTry1.isSuccess());

        IO.println("[Line 094] =====> Immutable Vectors");
        ImmutableVector<String> immutableVector = ImmutableVector.fill(5, "String");
        IO.println("[Line 096] " + immutableVector.size());

        IO.println("[Line 098] =====> Lazy");
        LazyValue<String> lazyValue = LazyValue.of(() -> System.getProperty("os.name").toLowerCase());
        IO.println("[Line 100] " + lazyValue.isReady());
        IO.println("[Line 101] " + lazyValue.get());
        IO.println("[Line 102] " + lazyValue.get().contains("windows"));
        IO.println("[Line 103] " + lazyValue.isReady());

        LateInitValue<String> lateInitValue = new LateInitValue<>();
        IO.println("[Line 106] " + lateInitValue.isInitialized());
        lateInitValue.initialize("sss");
        IO.println("[Line 108] " + lateInitValue.isInitialized());
    }
}
