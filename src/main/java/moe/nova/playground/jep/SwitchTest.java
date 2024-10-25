package moe.nova.playground.jep;

import kala.control.Result;

public class SwitchTest {

    public static <T, E extends Exception> String toStr(Result<T, E> result) {
        return switch (result) {
            case Result.Ok(var t) -> String.format("Ok[%s]", t.toString());
            case Result.Err(var e) -> String.format("Err[%s]", e.getMessage());
        };
    }

    public static void main(String[] args) {
    }
}
