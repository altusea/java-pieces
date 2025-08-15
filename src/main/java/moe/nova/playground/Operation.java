package moe.nova.playground;

enum Operation {
    ADD {
        @Override
        double apply(double x, double y) {
            return x + y;
        }
    },
    SUBTRACT {
        @Override
        double apply(double x, double y) {
            return x - y;
        }
    },
    MULTIPLY {
        @Override
        double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE {
        @Override
        double apply(double x, double y) {
            return x / y;
        }
    };

    // Abstract method that each enum constant must implement
    abstract double apply(double x, double y);

    static void main() {
        IO.println(ADD.apply(1, 2));
        IO.println(MULTIPLY.apply(3, 5));
        IO.println(Operation.class);
        IO.println(Operation.ADD.getClass());
        IO.println(Operation.MULTIPLY.getClass());
    }
}
