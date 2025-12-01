package oleg.sopilnyak.brace.minimum.swap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AlgorithmTest {

    @Test
    void shouldCalculateMinSwapsToBalance() {

        assertEquals(0, Algorithm.minSwapsToBalance("{{}}{}"));
        assertEquals(3, Algorithm.minSwapsToBalance("}{{}}{{{"));
    }
}