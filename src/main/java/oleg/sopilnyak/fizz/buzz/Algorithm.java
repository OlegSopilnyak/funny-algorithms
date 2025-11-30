package oleg.sopilnyak.fizz.buzz;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

class Algorithm {

    static class LinksChain {
        final List<Link> chain;

        LinksChain(List<Link> chain) {
            this.chain = chain;
        }

        String transform(int n) {
            StringBuilder result = new StringBuilder();
            chain.stream().filter(link -> link.isItMine.test(n))
                    .map(link -> link.transformer.apply(n)).forEach(result::append);
            return result.toString();
        }
    }

    static class Link {
        final IntPredicate isItMine;
        final Function<Integer, String> transformer;

        Link(IntPredicate isMine, Function<Integer, String> transformer) {
            this.isItMine = isMine;
            this.transformer = transformer;
        }
    }

    private static final LinksChain fizzBuzzChain;

    static {
        IntPredicate fizz = i -> i % 3 == 0;
        IntPredicate buzz = i -> i % 5 == 0;
        fizzBuzzChain = new LinksChain(Arrays.asList(
                new Link(fizz, i -> "Fizz"),
                new Link(buzz, i -> "Buzz"),
                new Link(fizz.or(buzz).negate(), String::valueOf))
        );
    }

    static void fizzBuzz(int n) {
        fizzBuzz(n, System.out::println);
    }

    static void fizzBuzz(int n, Consumer<String> resultOutput) {
        IntStream.rangeClosed(1, n).mapToObj(fizzBuzzChain::transform).forEach(resultOutput);
    }
}
