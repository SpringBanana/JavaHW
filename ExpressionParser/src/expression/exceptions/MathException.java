package expression.exceptions;

public class MathException extends EvaluationException {
    public MathException(String MathException) {
        super("Math Exception: " + MathException);
    }
}
