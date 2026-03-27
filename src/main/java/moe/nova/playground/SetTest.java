package moe.nova.playground;

import java.util.List;
import java.util.Set;

public class SetTest {

    static void main() {
        Set<String> a = Set.of("a", "b", "c");
        try {
            var _ = a.contains(null);
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
        List<String> b = List.of("a", "b", "c");
        try {
            var _ = b.contains(null);
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }
}
