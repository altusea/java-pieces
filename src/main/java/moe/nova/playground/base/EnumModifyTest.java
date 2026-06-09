package moe.nova.playground.base;

public class EnumModifyTest {

    enum Color {
        RED("red"),
        GREEN("green"),
        BLUE("blue");

        private String code;

        Color(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    static void main() {
        Color.RED.code = "black";
        new Thread(() -> System.out.println(Color.RED.code)).start();
        new Thread(() -> System.out.println(Color.RED.code)).start();
    }
}
