package moe.nova.playground;

record Point(double x, double y) {
}

sealed interface Shape {
}

record Circle(Point center, double radius) implements Shape {
}

record Rectangle(Point topLeft, Point bottomRight) implements Shape {
}

record Triangle(Point p1, Point p2, Point p3) implements Shape {
}

public class DateOrientedDemo {

    static int numOfEdges(Shape shape) {
        return switch (shape) {
            case Circle _ -> 0;
            case Rectangle _ -> 4;
            case Triangle _ -> 3;
        };
    }

    static Point getCenter(Shape shape) {
        return switch (shape) {
            case Circle(Point center, double _) -> new Point(center.x(), center.y());
            case Rectangle(Point topLeft, Point bottomRight) -> new Point(
                    (topLeft.x() + bottomRight.x()) / 2,
                    (topLeft.y() + bottomRight.y()) / 2
            );
            case Triangle(Point p1, Point p2, Point p3) -> new Point(
                    (p1.x() + p2.x() + p3.x()) / 3,
                    (p1.y() + p2.y() + p3.y()) / 3
            );
        };
    }

    public static void main(String[] args) {
        var circle = new Circle(new Point(0, 0), 5);
        var rectangle = new Rectangle(new Point(2, 2), new Point(10, 10));
        var triangle = new Triangle(new Point(0, 0), new Point(5, 0), new Point(0, 12));

        System.out.println(numOfEdges(circle));
        System.out.println(getCenter(rectangle));
        System.out.println(getCenter(triangle));
    }
}
