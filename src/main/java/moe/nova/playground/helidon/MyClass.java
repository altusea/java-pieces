package moe.nova.playground.helidon;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * 使用 VarHandle 来访问和修改类的字段，包括静态字段和实例字段
 */
class MyClass {
    private int myField;

    static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        MyClass instance = new MyClass();

        // 获取 VarHandle 来访问 myField
        VarHandle fieldHandle = MethodHandles.lookup().in(MyClass.class)
                .findVarHandle(MyClass.class, "myField", int.class);

        // 使用 VarHandle 来读取和修改字段值
        int currentValue = (int) fieldHandle.get(instance);
        fieldHandle.set(instance, currentValue + 1);
        IO.println(instance.myField);
    }
}
