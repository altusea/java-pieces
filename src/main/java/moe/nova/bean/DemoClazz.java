package moe.nova.bean;

import moe.nova.util.security.AbstractedTimestamped;

public class DemoClazz extends AbstractedTimestamped {

    private String fieldA;

    private String fieldB;

    public String getFieldA() {
        return fieldA;
    }

    public void setFieldA(String fieldA) {
        this.fieldA = fieldA;
    }

    public String getFieldB() {
        return fieldB;
    }

    public void setFieldB(String fieldB) {
        this.fieldB = fieldB;
    }
}
