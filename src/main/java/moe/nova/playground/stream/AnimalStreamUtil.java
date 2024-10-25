package moe.nova.playground.stream;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Gatherer;
import java.util.stream.Gatherers;

import static moe.nova.playground.stream.Animal.*;

public class AnimalStreamUtil {

    public static Gatherer<Animal, ?, Boolean> isValidSequence() {
        Predicate<List<Animal>> validTriplePredicate = triple -> !(
                triple.contains(SHEEP) && triple.contains(WOLF) && !triple.contains(SHEEP_DOG)
        );

        var tripleWindowGatherer = Gatherers.<Animal>windowSliding(3);
        var areAllTriplesValidGatherer = Gatherers.<List<Animal>, Boolean>fold(
                () -> true,
                (result, triple) -> result && validTriplePredicate.test(triple)
        );

        return tripleWindowGatherer.andThen(areAllTriplesValidGatherer);
    }

    public static void main(String[] args) {
        var validSequence = List.of(SHEEP, SHEEP_DOG, WOLF, WOLF);
        System.out.println("Valid animal sequence result:");
        validSequence.stream()
                .gather(AnimalStreamUtil.isValidSequence())
                .forEach(System.out::println);

        var invalidSequence = List.of(SHEEP_DOG, SHEEP, WOLF, WOLF);
        System.out.println("\nInvalid animal sequence result:");
        invalidSequence.stream()
                .gather(AnimalStreamUtil.isValidSequence())
                .forEach(System.out::println);
    }
}
