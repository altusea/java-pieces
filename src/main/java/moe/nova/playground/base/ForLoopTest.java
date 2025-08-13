package moe.nova.playground.base;

import java.util.ArrayList;
import java.util.List;

public class ForLoopTest {

    static void main() {
        List<Runnable> toDoList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            int j = i;
            toDoList.add(() -> IO.println("Do item #" + j));
        }
        for (Runnable runnable : toDoList) {
            runnable.run();
        }
    }
}
