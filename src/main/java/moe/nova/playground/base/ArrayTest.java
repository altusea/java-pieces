package moe.nova.playground.base;

public class ArrayTest {

    public static void test(String... strList) {
        if (strList == null) {
            System.out.println("input is null");
        } else if (strList.length == 0) {
            System.out.println("input is empty");
        } else {
            System.out.println("input is not empty");
        }
    }

    public static void main(String[] args) {
        test(); // will print "input is empty"
        test("a"); // will print "input is not empty"
    }
}
