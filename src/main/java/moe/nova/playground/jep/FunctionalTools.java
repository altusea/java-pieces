package moe.nova.playground.jep;

public class FunctionalTools {

    public static <State, Event> When<State, Event> when(State state, Event event) {
        return new When<>(state, event);
    }

    public static <State, Event> On<State, Event> on(State state, Event event) {
        return new On<>(state, event);
    }

    public record When<State, Event>(State state, Event event) {
    }

    public record On<State, Command>(State state, Command command) {
    }

}
