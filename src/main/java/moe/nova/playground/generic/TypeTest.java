package moe.nova.playground.generic;

import com.google.common.reflect.TypeToken;
import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class TypeTest {

    public static void main(String[] args) {
        System.out.println(Exception.class.isAssignableFrom(RuntimeException.class));
        System.out.println(RuntimeException.class.isAssignableFrom(Exception.class));
        System.out.println(Exception.class.isInstance(new RuntimeException()));

        printSeparateLine();
        System.out.println(Set.class.isAssignableFrom(HashSet.class));
        System.out.println(HashSet.class.isAssignableFrom(Set.class));

        printSeparateLine();
        var token = new TypeToken<List<String>>() {
        };
        System.out.println(token.getRawType());
        System.out.println(token.isSubtypeOf(List.class));

        printSeparateLine();
        WildcardType b = TypeUtils.wildcardType().withUpperBounds(Collection.class).build();
        System.out.println(b);
    }
}
