package moe.nova.playground.memory;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

public class AllocDemo {

    static void main() {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment cString = arena.allocateFrom("Panama");
            String jString = cString.getString(0L);
            IO.println(jString);
        }
    }

}
