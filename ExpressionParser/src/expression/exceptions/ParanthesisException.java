package expression.exceptions;

public class ParanthesisException extends ParsingException {
    public ParanthesisException(String s, int pos, String expression) {
        super("No " + s + " paranthesis" + pos + '\n' + expression + '\n' + " ".repeat(pos - 1) + '^');
    }
}
