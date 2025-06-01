package moe.nova.playground.jep;

import kala.control.Result;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class SwitchTest {

    public static <T, E extends Exception> String toStr(Result<T, E> result) {
        return switch (result) {
            case Result.Ok(var t) -> String.format("Ok[%s]", t.toString());
            case Result.Err(var e) -> String.format("Err[%s]", e.getMessage());
        };
    }

    static void main(String[] args) {
        IO.println(toStr(new Result.Ok<>("hello")));
        IO.println(toStr(new Result.Err<>(new RuntimeException("error"))));

        printSeparateLine();
        var stringList = List.of("Foo", "Bar", "Baz");
        var s = stringList.get(RandomUtils.secure().randomInt(0, 3));
        int result = switch (s) {
            case "Foo":
                yield 1;
            case "Bar":
                yield 2;
            default:
                IO.println("Neither Foo nor Bar, hmmm...");
                yield 0;
        };
        IO.println(result);
    }
}
