package moe.nova.playground.base;

public class ArrayTest {

    public static void test(String... strList) {
        if (strList == null) {
            IO.println("input is null");
        } else if (strList.length == 0) {
            IO.println("input is empty");
        } else {
            IO.println("input is not empty");
        }
    }

    public static void main(String[] args) {
        test(); // will print "input is empty"
        test("a"); // will print "input is not empty"
    }
}
