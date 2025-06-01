package moe.nova.playground;

import com.google.common.collect.Lists;
import moe.nova.bean.Organization;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class TreeTest {

    public static <T, E> void buildTree(Collection<T> data,
                                        Function<T, E> idMapper,
                                        Function<T, E> parentMapper,
                                        BiConsumer<T, List<T>> consumer) {
        ArrayListValuedHashMap<E, T> multiValuedMap = new ArrayListValuedHashMap<>();
        for (T element : data) {
            E parentId = parentMapper.apply(element);
            if (parentId != null) {
                multiValuedMap.put(parentId, element);
            }
        }
        for (T element : data) {
            E id = idMapper.apply(element);
            if (multiValuedMap.containsKey(id)) {
                consumer.accept(element, multiValuedMap.get(id));
            }
        }
    }

    static void main(String[] args) {
        Collection<Organization> organizationList = Lists.newArrayList(
                new Organization(0L, "A", null),
                new Organization(1L, "B", 0L),
                new Organization(2L, "C", 0L),
                new Organization(3L, "D", 1L)
        );

        buildTree(organizationList, Organization::getId, Organization::getParentId, Organization::setChildren);
        organizationList.forEach(System.out::println);
    }

}
