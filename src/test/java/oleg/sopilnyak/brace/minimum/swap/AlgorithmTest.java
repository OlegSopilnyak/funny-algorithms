package oleg.sopilnyak.brace.minimum.swap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AlgorithmTest {

    @Test
    void shouldCalculateMinSwapsToBalance() {

        assertEquals(0, Algorithm.minSwapsToBalance("{{}}{}"));
        assertEquals(3, Algorithm.minSwapsToBalance("}}{{"));
        assertEquals(2, Algorithm.minSwapsToBalance("{}}{}{"));
    }

    @Test
    void shouldNotCalculateMinSwapsToBalance() {

        assertEquals(-1, Algorithm.minSwapsToBalance("}{{}}{{{"));
    }
}