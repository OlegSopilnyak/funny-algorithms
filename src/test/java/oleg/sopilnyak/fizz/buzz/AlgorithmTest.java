package oleg.sopilnyak.fizz.buzz;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class AlgorithmTest {

    @DisplayName("Testing FizzBuzz algorithm for 15 counters")
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

    @DisplayName("Testing FizzBuzz algorithm for the counters range between 20 to 200")
    @ParameterizedTest
    @MethodSource("provideIntRange")
    void shouldProcessFizzBuzz_from_20_to_200(int expected) {
        List<String> result = new ArrayList<>(expected);

        Algorithm.fizzBuzz(expected, result::add);

        assertThat(result).hasSize(expected);
        IntStream.range(0, expected).forEach(i -> checkValue(i + 1, result.get(i)));
    }

    // private methods
    private static Stream<Integer> provideIntRange(){
        return IntStream.rangeClosed(20, 200).boxed();
    }

    private static void checkValue(int counter, String value) {
        if (counter % 3 == 0 && counter % 5 == 0) {
            assertThat(value).isEqualTo("FizzBuzz");
        } else if (counter % 3 == 0) {
            assertThat(value).isEqualTo("Fizz");
        } else if (counter % 5 == 0) {
            assertThat(value).isEqualTo("Buzz");
        } else  {
            assertThat(value).isEqualTo(String.valueOf(counter));
        }
    }
}