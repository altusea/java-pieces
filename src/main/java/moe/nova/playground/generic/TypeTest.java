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

    static void main() {
        IO.println(Exception.class.isAssignableFrom(RuntimeException.class));
        IO.println(RuntimeException.class.isAssignableFrom(Exception.class));
        IO.println(Exception.class.isInstance(new RuntimeException()));

        printSeparateLine();
        IO.println(Set.class.isAssignableFrom(HashSet.class));
        IO.println(HashSet.class.isAssignableFrom(Set.class));

        printSeparateLine();
        var token = new TypeToken<List<String>>() {
        };
        IO.println(token.getRawType());
        IO.println(token.isSubtypeOf(List.class));

        printSeparateLine();
        WildcardType b = TypeUtils.wildcardType().withUpperBounds(Collection.class).build();
        IO.println(b);
    }
}
