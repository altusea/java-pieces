package moe.nova.playground.jep;

public sealed interface IntOp {

    int op(int i);

    static int opOn(int i, IntOp intOp) {
        return switch (intOp) {
            case AddOne addTen -> addTen.op(i);
            case MulTwo mulTwo -> mulTwo.op(i);
        };
    }
}

final class AddOne implements IntOp {

    @Override
    public int op(int i) {
        return i + 1;
    }
}

final class MulTwo implements IntOp {

    @Override
    public int op(int i) {
        return i * 2;
    }
}

class IntOpTest {
    public static void main(String[] args) {
        int i = 10;
        var op1 = new AddOne();
        System.out.println(IntOp.opOn(i, op1));
        var op2 = new MulTwo();
        System.out.println(IntOp.opOn(i, op2));
    }
}
