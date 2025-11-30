package oleg.sopilnyak.brace;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Brace {
    private char open;
    private char close;

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
