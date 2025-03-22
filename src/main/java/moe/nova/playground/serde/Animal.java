package moe.nova.playground.serde;

import moe.nova.util.JacksonUtil;

public sealed class Animal permits Dog, Cat {

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        var a = new Dog();
        a.setAge(10);
        a.setSpices("white");
        System.out.println(JacksonUtil.toJson(a));
        var b = new Cat();
        b.setAge(10);
        b.setFurColor("white");
        System.out.println(JacksonUtil.toJson(b));
    }
}
