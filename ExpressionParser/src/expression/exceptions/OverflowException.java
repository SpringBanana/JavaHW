package expression.exceptions;

public class OverflowException extends NumberFormatException{
    public OverflowException() {
        super("Overflow!");
    }
}
