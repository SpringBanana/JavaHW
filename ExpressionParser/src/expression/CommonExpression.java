package expression;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.EvaluationException;
import expression.exceptions.OverflowException;

public interface CommonExpression extends Expression, TripleExpression {

    @Override
    default int evaluate(int x) throws OverflowException, EvaluationException {
        return (evaluate(x, 0, 0));
    }

    int getPriority();

    default boolean isCommutative() {
        return true;
    }
}
