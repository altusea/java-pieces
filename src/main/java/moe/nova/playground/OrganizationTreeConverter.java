package moe.nova.playground;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.MultimapBuilder;
import moe.nova.bean.Organization;

import java.util.Collection;

public class OrganizationTreeConverter {

    static void main(String[] args) {
        Collection<Organization> organizationList = Lists.newArrayList(
                new Organization(0L, "A", null),
                new Organization(1L, "B", 0L),
                new Organization(2L, "C", 0L),
                new Organization(3L, "D", 1L)
        );

        ListMultimap<Long, Organization> treeListMultimap = MultimapBuilder.treeKeys().arrayListValues().build();
        for (Organization organization : organizationList) {
            if (organization.getParentId() != null) {
                treeListMultimap.put(organization.getParentId(), organization);
            }
        }

        organizationList.forEach(e -> e.setChildren(treeListMultimap.get(e.getId())));
        organizationList.forEach(System.out::println);
    }
}
