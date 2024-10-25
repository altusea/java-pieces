package moe.nova.bean;

import org.apache.commons.lang3.builder.DiffBuilder;
import org.apache.commons.lang3.builder.DiffResult;
import org.apache.commons.lang3.builder.Diffable;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Person implements Diffable<Person> {
    String name;
    int age;
    boolean smoker;

    public DiffResult<Person> diff(Person obj) {
        // No need for null check, as NullPointerException correct if obj is null
        return DiffBuilder.<Person>builder()
                .setLeft(this)
                .setRight(obj)
                .setStyle(ToStringStyle.SHORT_PREFIX_STYLE)
                .build()
                .append("name", this.name, obj.name)
                .append("age", this.age, obj.age)
                .append("smoker", this.smoker, obj.smoker)
                .build();
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.name = "John";
        person.age = 18;
        person.smoker = true;

        Person person2 = new Person();
        person2.name = "Jane";
        person2.age = 19;
        person2.smoker = false;

        System.out.println(person.diff(person2).getDiffs());
    }
}
