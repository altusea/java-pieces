package moe.nova.playground.jep;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleTest {

    public static void main(String[] args) throws Throwable {
        var lookup = MethodHandles.lookup();
        MethodHandle targetMh = lookup.findStatic(MethodHandleTest.class, "target", MethodType.methodType(void.class));
        targetMh.invoke(); // prints 'invoking target'
    }

    public static void target() {
        System.out.println("invoking target");
    }

}
