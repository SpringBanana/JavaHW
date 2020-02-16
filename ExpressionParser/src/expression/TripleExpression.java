package expression;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.EvaluationException;
import expression.exceptions.OverflowException;

public interface TripleExpression extends ToMiniString {
    int evaluate(int x, int y, int z) throws OverflowException, EvaluationException;
}