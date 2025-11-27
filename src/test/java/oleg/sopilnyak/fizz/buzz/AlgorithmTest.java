package oleg.sopilnyak.fizz.buzz;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class AlgorithmTest {

    @Test
    void shouldProcessFizzBuzz_15() {
        int expected = 15;
        String[] expectedResult = new String[]{
                "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz"
        };
        List<String> result = new ArrayList<>(expected);

        Algorithm.fizzBuzz(expected, result::add);

        assertThat(result).isEqualTo(Arrays.asList(expectedResult));
    }

    @ParameterizedTest
    @MethodSource("provideIntRange")
    void shouldProcessFizzBuzz_from_20_to_200(int expected) {
        List<String> result = new ArrayList<>(expected);

        Algorithm.fizzBuzz(expected, result::add);

        assertThat(result).hasSize(expected);
    }

    private static Stream<Integer> provideIntRange(){
        return IntStream.rangeClosed(20, 200).boxed();
    }
}