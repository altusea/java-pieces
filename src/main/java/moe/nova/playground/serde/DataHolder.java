package moe.nova.playground.serde;

public class DataHolder {

    private String field;

    private InnerClazz innerClazz;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public InnerClazz getInnerClazz() {
        return innerClazz;
    }

    public void setInnerClazz(InnerClazz innerClazz) {
        this.innerClazz = innerClazz;
    }

    @Override
    public String toString() {
        return "DataHolder{" +
                "field='" + field + '\'' +
                ", innerClazz=" + innerClazz +
                '}';
    }

    public static class InnerClazz {

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

        @Override
        public String toString() {
            return "InnerClazz{" +
                    "fieldA='" + fieldA + '\'' +
                    ", fieldB='" + fieldB + '\'' +
                    '}';
        }
    }

}
