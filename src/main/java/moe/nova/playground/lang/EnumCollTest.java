package moe.nova.playground.lang;

import org.apache.commons.lang3.EnumUtils;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class EnumCollTest {

    enum Color {

        RED("red"),
        GREEN("green"),
        BLUE("blue"),
        ;

        private final String code;

        Color(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    enum EmployeePosition {
        SRE,
        ARCHITECT,
        DEVELOPER
    }

    public static void main(String[] args) {
        Map<String, Color> map = EnumUtils.getEnumMap(Color.class, Color::getCode);
        System.out.println(map.get(Color.RED.code));

        // with EnumSet
        printSeparateLine();
        var x = EnumSet.of(
                EmployeePosition.SRE,
                EmployeePosition.ARCHITECT,
                EmployeePosition.DEVELOPER);
        long begX = System.nanoTime();
        for (int i = 0; i < 100_000_000; i++) {
            var es = EnumSet.allOf(EmployeePosition.class);
            var _ = es.containsAll(x);
        }
        long endX = System.nanoTime();
        System.out.println(x.getClass() + ": " + (endX - begX) / 1e9);

        // with Set
        printSeparateLine();
        var y = Set.of(
                EmployeePosition.SRE,
                EmployeePosition.ARCHITECT,
                EmployeePosition.DEVELOPER);
        long begY = System.nanoTime();
        for (int i = 0; i < 100_000_000; i++) {
            var hs = Set.of(EmployeePosition.values());
            var _ = hs.containsAll(y);
        }
        long endY = System.nanoTime();
        System.out.println(y.getClass() + ": " + (endY - begY) / 1e9);

        EnumMap<EmployeePosition, Long> map2 = new EnumMap<>(EmployeePosition.class);
        map2.put(EmployeePosition.SRE, 1L);
        map2.put(EmployeePosition.ARCHITECT, 2L);
        map2.put(EmployeePosition.DEVELOPER, 3L);
        System.out.println(map2.containsKey(EmployeePosition.SRE));
    }
}
