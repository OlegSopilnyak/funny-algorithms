package oleg.sopilnyak.brace.balanced;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AlgorithmTest {

    @Test
    void shouldBeBalancedParentheses() {

        assertTrue(Algorithm.isBalancedParentheses("()(){}{}{()}"));
        assertTrue(Algorithm.isBalancedParentheses("(a+2)(  good parentheses-> ){rrr}{}{2*(5/2)}"));
    }

    @Test
    void shouldNotBeBalancedParentheses() {

        assertFalse(Algorithm.isBalancedParentheses("((({}{}))()"));
        assertFalse(Algorithm.isBalancedParentheses("()(){}{} bad parentheses-> ({)} "));
    }
}