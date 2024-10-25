package moe.nova.playground.lang;

import org.apache.commons.lang3.EnumUtils;

public class EnumTest {

    enum Color {

        RED("RED", 255, 0, 0),
        GREEN("GREEN", 0, 255, 0),
        BLUE("BLUE", 0, 0, 255);

        final String code;

        final int r;

        final int g;

        final int b;

        Color(String code, int r, int g, int b) {
            this.code = code;
            this.r = r;
            this.g = g;
            this.b = b;
        }

        public String getCode() {
            return code;
        }

        public int getR() {
            return r;
        }

        public int getG() {
            return g;
        }

        public int getB() {
            return b;
        }

        @Override
        public String toString() {
            return "Color[" +
                    code +
                    ", r=" + r +
                    ", g=" + g +
                    ", b=" + b +
                    ']';
        }
    }

    public static void main(String[] args) {
        var map = EnumUtils.getEnumMap(Color.class, Color::getCode);
        System.out.println(map.size());
        var red = map.get(Color.RED.code);
        System.out.println(red);
    }
}
