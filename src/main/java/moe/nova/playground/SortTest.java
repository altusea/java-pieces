package moe.nova.playground;

import moe.nova.util.ConsoleUtil;

import java.util.Comparator;
import java.util.stream.Stream;

public class SortTest {

    static class Endable {

        Endable() {
        }

        Endable(String endDate) {
            this.endDate = endDate;
        }

        String endDate;

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        @Override
        public String toString() {
            return "Endable{endDate='" + endDate + "'}";
        }
    }

    static void main() {
        var nullEndable = new Endable(null);
        var endable0 = new Endable("2026-07-01");
        var endable1 = new Endable("2025-01-01");
        var endable2 = new Endable("2027-01-01");
        var endable3 = new Endable("2026-01-01");

        Stream.of(nullEndable, endable0, endable1, endable2, endable3)
                .sorted(Comparator.comparing(Endable::getEndDate, Comparator.nullsLast(Comparator.naturalOrder())))
                .forEachOrdered(System.out::println);

        ConsoleUtil.printSeparateLine();

        Stream.of(nullEndable, endable0, endable1, endable2, endable3)
                .sorted(Comparator.comparing(Endable::getEndDate, Comparator.nullsLast(Comparator.reverseOrder())))
                .forEachOrdered(System.out::println);
    }
}
