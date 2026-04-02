package moe.nova.playground;

import java.util.HashSet;
import java.util.Set;

public class RecordTest {

    record Triple(String a, String b, String c) {
    }

    static void main(String[] args) {
        Triple ta0 = new Triple("1", "0", "1");
        Triple tb0 = new Triple("1", "1", "1");
        Triple ta1 = new Triple("1", "0", "1");
        Triple tb1 = new Triple("1", "1", "1");
        Triple tc0 = new Triple("1", "2", "1");
        Set<Triple> set = new HashSet<>();
        System.out.println(set.add(ta0));
        System.out.println(set.add(tb0));
        System.out.println(set.add(ta1));
        System.out.println(set.add(tb1));
        System.out.println(set.add(tc0));
    }
}
