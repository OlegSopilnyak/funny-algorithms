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
        BraceToSwap brace = BraceToSwap.builder().open('{').close('}').balanceMatter(true).build();
        if (isNull(expression) || expression.trim().isEmpty()) {
            return -1;
        }
        expression.chars().mapToObj(c -> (char) c).forEach(brace::update);
        return brace.swapsCount();
    }

    // nested classes
    @SuperBuilder
    private static class BraceToSwap extends BracesSort {
        // Quantity of braces to swap
        @Builder.Default
        private int toSwap = 0;
        // How many unmatched closing braces we have
        @Builder.Default
        int balance = 0;
        // counters of open/close braces
        @Builder.Default
        private int countLeft = 0;
        @Builder.Default
        private int countRight = 0;
        @Builder.Default
        // flag do we need balanced braces
        private boolean balanceMatter = false;

        public void update(char symbol) {
            if (!myOwnSymbolValue(symbol)) {
                // skip non brace symbol
                return;
            }

            if (symbol == open) {
                // left (open) brace's symbol
                balance++;
                countLeft++;
            } else {
                // right (close) brace's symbol
                balance--;
                countRight++;
            }
            // If balance goes negative, we have more closing than opening so far
            if (balance < 0) {
                toSwap++;
                balance = countRight - countLeft; // After swap, we have one more opening than closing
            }
        }

        public int swapsCount() {
            return countRight != countLeft && balanceMatter ? -1 : toSwap;
        }
    }
}
