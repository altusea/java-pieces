package moe.nova.playground.jep;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleTest {

    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType typeOfTarget = MethodType.methodType(void.class);
        MethodHandle targetMh = lookup.findStatic(MethodHandleTest.class, "target", typeOfTarget);

        targetMh.invoke(); // prints 'invoking target'
    }

    public static void target() {
        System.out.println("invoking target");
    }

}
