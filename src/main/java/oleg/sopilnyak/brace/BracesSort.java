package oleg.sopilnyak.brace;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * Te parent of any kind braces
 */
@Data
@SuperBuilder
public class BracesSort {
    protected final char open;
    protected final char close;

    public boolean isOpening(char symbol) {
        return open == symbol;
    }

    public boolean isClosing(char symbol) {
        return close == symbol;
    }

    public boolean myOwnSymbolValue(char symbol) {
        return open == symbol || close == symbol;
    }
}
