package moe.nova.playground.helidon;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * VarHandle 可以用来操作数组元素，提供了原子性的保证
 */
class MyArrayClass {
    private int[] myArray;

    static void main(String[] args) {
        MyArrayClass instance = new MyArrayClass();
        instance.myArray = new int[10];

        // 获取 VarHandle 来访问数组元素
        VarHandle arrayElementHandle = MethodHandles.arrayElementVarHandle(int[].class);

        // 使用 VarHandle 来读取和修改数组元素
        int currentValue = (int) arrayElementHandle.get(instance.myArray, 0);
        arrayElementHandle.set(instance.myArray, 0, currentValue + 1);
        IO.println(ArrayUtils.toString(instance.myArray));
    }
}
