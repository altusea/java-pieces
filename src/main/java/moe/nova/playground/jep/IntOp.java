package moe.nova.playground.jep;

public sealed interface IntOp {

    int op(int i);

    static int opOn(int i, IntOp intOp) {
        return switch (intOp) {
            case Increment increment -> increment.op(i);
            case Redouble redouble -> redouble.op(i);
            case Halve halve -> halve.op(i);
        };
    }
}

final class Increment implements IntOp {

    @Override
    public int op(int i) {
        return i + 1;
    }
}

final class Redouble implements IntOp {

    @Override
    public int op(int i) {
        return i * 2;
    }
}

final class Halve implements IntOp {

    @Override
    public int op(int i) {
        return i / 2;
    }
}

class IntOpTest {
    public static void main(String[] args) {
        int i = 10;
        var op1 = new Increment();
        System.out.println(IntOp.opOn(i, op1));
        var op2 = new Redouble();
        System.out.println(IntOp.opOn(i, op2));
        var op3 = new Halve();
        System.out.println(IntOp.opOn(i, op3));
    }
}
