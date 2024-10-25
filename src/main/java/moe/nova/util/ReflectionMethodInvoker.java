package moe.nova.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionMethodInvoker<T, R> {

    private final Class<T> clazz;
    private final String methodName;
    private final Class<R> returnType;
    private final Class<?>[] parameterTypes;

    private Method targetMethod;

    public ReflectionMethodInvoker(Class<T> clazz,
                                   Class<R> returnType,
                                   String methodName,
                                   Class<?>... parameterTypes) {
        this.clazz = clazz;
        this.methodName = methodName;
        this.returnType = returnType;
        this.parameterTypes = parameterTypes.clone();
    }

    public R invoke(T obj, Object... args) throws NoSuchMethodException {
        Method targetMethod = getTargetMethod();

        try {
            Object rawResult = targetMethod.invoke(obj, args);
            return returnType.cast(rawResult);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(createInvocationErrorMessage(), e);
        }
    }

    public void initialize() throws NoSuchMethodException {
        getTargetMethod();
    }

    public boolean isInitialized() {
        return targetMethod != null;
    }

    private Method getTargetMethod() throws NoSuchMethodException {
        if (targetMethod != null) {
            return targetMethod;
        }

        try {
            targetMethod = clazz.getMethod(methodName, parameterTypes);
            return targetMethod;
        } catch (RuntimeException e) {
            throw new RuntimeException(createInvocationErrorMessage(), e);
        }
    }

    private String createInvocationErrorMessage() {
        return String.format("Failed to reflectively invoke method %s on %s", methodName, clazz.getName());
    }
}
