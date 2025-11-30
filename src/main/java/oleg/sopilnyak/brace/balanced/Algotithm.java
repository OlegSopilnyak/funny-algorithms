package oleg.sopilnyak.brace.balanced;

import oleg.sopilnyak.brace.Brace;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import lombok.experimental.SuperBuilder;

/**
 * checks whether the string is a balanced parenthesized expression.
 */
public class Algotithm {
    static List<BalancedBrace> braces = Arrays.asList(new BalancedBrace[]{
            BalancedBrace.builder().open('(').close(')').build(),
            BalancedBrace.builder().open('{').close('}').build(),
            BalancedBrace.builder().open('[').close(']').build()
    });


    /**
     * checking expression for Parentheses
     */
    static boolean iBalancedParentheses(String expression) {
        Stack<Character> bracesStack = new Stack<>();
        for (char symbol : expression.toCharArray()) {
            Optional<BalancedBrace> foundBrace = findBySymbol(symbol);
            if (foundBrace.isPresent()) {
                BalancedBrace brace = foundBrace.get();
                if (brace.isOpening(symbol)) {
                    bracesStack.push(symbol);
                } else if (brace.isClosing(symbol) && (bracesStack.isEmpty() || bracesStack.pop() != symbol)) {
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
    private static class BalancedBrace extends Brace {}
}
