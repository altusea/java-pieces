package moe.nova.playground;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;

public class EclipseCollectionsTest {

    static void main() {
        var a = Lists.immutable.of(0L, 1L, 2L);
        var b = a.zipWithIndex();
        System.out.println(b);

        var s = UnifiedSet.<String>newSet();
        s.add("apple");
        s.add("banana");
        s.add("orange");
        s.add("apple");
        System.out.println(s);

        MutableListMultimap<String, String> m = new FastListMultimap<>();
        m.put("a", "1");
        m.put("a", "2");
        m.put("b", "3");
        System.out.println(m);
    }
}
