package expression.exceptions;

public class OverflowException extends MathException {
    public OverflowException(String argument) {
        super("Integer overflow" + argument);
    }
}
