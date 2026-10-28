package moe.nova.playground;

import kala.tuple.Tuple2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortList {

    static void main() {
        List<Tuple2<String, LocalDate>> list =
                List.of(
                        new Tuple2<>("110000", LocalDate.of(2020, 1, 1)),
                        new Tuple2<>("130001", LocalDate.of(2023, 1, 1)),
                        new Tuple2<>("130000", LocalDate.of(2022, 1, 1)),
                        new Tuple2<>("120000", LocalDate.of(2021, 1, 1)),
                        new Tuple2<>("120000", LocalDate.of(2019, 1, 1))
                );
        // 13 开头的排前面（false < true），同组内再按日期升序
        Comparator<Tuple2<String, LocalDate>> comparator =
                Comparator.comparing((Tuple2<String, LocalDate> t) -> !t.component1().startsWith("13"))
                        .thenComparing(Tuple2::component2);

        List<Tuple2<String, LocalDate>> sorted = new ArrayList<>(list);
        sorted.sort(comparator);

        sorted.forEach(System.out::println);
    }

}
