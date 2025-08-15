package moe.nova.playground;

import java.util.Date;

public class Reified {

    @SafeVarargs
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(T... stub) {
        Class<T> reifiedType = (Class<T>) stub.getClass().getComponentType();
        try {
            return reifiedType.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException("Oops", exception);
        }
    }

    static void main() {
        // Inferred type:
        Date date = newInstance();
        System.out.println(date);
        // Explicit type:
        System.out.println(Reified.<Date>newInstance());
    }
}
