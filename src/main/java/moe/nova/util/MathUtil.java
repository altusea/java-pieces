package moe.nova.util;

public class MathUtil {

    private static double stepDown(double d) {
        return Math.nextAfter(d, Double.NEGATIVE_INFINITY);
    }

    private static double stepUp(double d) {
        return Math.nextUp(d);
    }

    public static double[] safeAdd(double a, double b) {
        return new double[]{stepDown(a + b), stepUp(a + b)};
    }

    static void main(String[] args) {
        IO.println(Math.ceilDiv(10, 3));
        IO.println(Math.ceilDiv(10, 2));
        IO.println(Math.ceilDiv(1L, 3L));
        double a = 1.2;
        double b = 0.03;
        double[] result = safeAdd(a, b);
        System.out.printf("(%.2f + %.2f) is in the range %.16f..%.16f%n", a, b, result[0], result[1]);

        IO.println(Math.ceilDiv(64, 5));
    }
}
