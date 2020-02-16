package expression.exceptions;

public class WrongSymbolException extends ParsingException {

    public WrongSymbolException(String error, int pos, String expression) {
        super("Incorrect Symbol " + error + "at position: " + pos + '\n' + expression + '\n' + " ".repeat(pos - 1) + '^');
    }
}
