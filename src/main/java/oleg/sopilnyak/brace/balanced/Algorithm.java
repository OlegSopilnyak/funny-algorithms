package oleg.sopilnyak.brace.balanced;

import oleg.sopilnyak.brace.BracesSort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import lombok.experimental.SuperBuilder;

/**
 * checks whether the string is a balanced parenthesized expression.
 */
public class Algorithm {
    static List<BalancedBrace> braces = Arrays.asList(
            BalancedBrace.builder().open('(').close(')').build(),
            BalancedBrace.builder().open('{').close('}').build(),
            BalancedBrace.builder().open('[').close(']').build()
    );


    /**
     * checking expression for Parentheses
     */
    static boolean isBalancedParentheses(String expression) {
        Stack<BalancedBrace> bracesStack = new Stack<>();
        for (char symbol : expression.toCharArray()) {
            Optional<BalancedBrace> foundBrace = findBySymbol(symbol);
            if (foundBrace.isPresent()) {
                BalancedBrace brace = foundBrace.get();
                if (brace.isOpening(symbol)) {
                    bracesStack.push(brace);
                } else if (brace.isClosing(symbol) && (bracesStack.isEmpty() || !bracesStack.pop().equals(brace))) {
                    return false;
                }
            }
        }
        return bracesStack.isEmpty();
    }

    // private methods
    private static Optional<BalancedBrace> findBySymbol(char symbol) {
        return braces.stream().filter(brace -> brace.myOwnSymbolValue(symbol)).findFirst();
    }

    // nested classes
    @SuperBuilder
    private static class BalancedBrace extends BracesSort {
    }
}
