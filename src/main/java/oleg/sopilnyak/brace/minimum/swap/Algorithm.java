package oleg.sopilnyak.brace.minimum.swap;

import static java.util.Objects.isNull;

import oleg.sopilnyak.brace.BracesSort;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

public class Algorithm {
    /**
     * Calculates the minimum number of swaps required to balance a string of braces.
     * Assumes the string contains only '{' and '}' and has an even length.
     *
     * @param expression the input string
     * @return minimum number of swaps needed
     */
    public static int minSwapsToBalance(String expression) {
        BraceToSwap brace = BraceToSwap.builder().open('(').close(')').build();
        if (isNull(expression) || expression.trim().isEmpty()) {
            return -1;
        }
        expression.chars().mapToObj(c -> (char) c).forEach(brace::update);
        return brace.swapsCount();
    }

    // nested classes
    @SuperBuilder
    private static class BraceToSwap extends BracesSort {
        @Builder.Default
        private int imbalance = 0; // Tracks how many unmatched closing braces we have
        @Builder.Default
        private int opened = 0; // Tracks number of unmatched opening braces
        public void update(char symbol) {
            if (symbol == open) {
                opened++;
            } if (symbol == close) {
                if (opened > 0) {
                    opened--;
                } else {
                    imbalance++;
                    opened = 0;
                }
            }
        }

        public  int swapsCount() {
            return opened > 0 ? -1 : (imbalance + 1) / 2;
        }
    }
}
