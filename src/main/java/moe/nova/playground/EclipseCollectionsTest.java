package moe.nova.playground;

import org.eclipse.collections.api.factory.Lists;

public class EclipseCollectionsTest {

    static void main() {
        var a = Lists.immutable.of(0L, 1L, 2L);
        var b = a.zipWithIndex();
        System.out.println(b);
    }
}
