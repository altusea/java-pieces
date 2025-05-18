package moe.nova.playground.helidon;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * VarHandle 提供了多种原子更新操作，如 compareAndSet、getAndSet 等
 */
class MyAtomicClass {
    private int myAtomicField;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        MyAtomicClass instance = new MyAtomicClass();

        // 获取 VarHandle 来访问 myAtomicField
        VarHandle atomicFieldHandle = MethodHandles.lookup().in(MyAtomicClass.class)
                .findVarHandle(MyAtomicClass.class, "myAtomicField", int.class);

        // 使用 compareAndSet 进行原子更新
        boolean success = atomicFieldHandle.compareAndSet(instance, 0, 10);
        if (success) {
            IO.println("Field updated atomically.");
        } else {
            IO.println("Field update failed.");
        }
    }
}
