package expression;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.EvaluationException;
import expression.exceptions.OverflowException;

public interface Expression extends ToMiniString {
    int evaluate(int x) throws OverflowException, EvaluationException;
}
