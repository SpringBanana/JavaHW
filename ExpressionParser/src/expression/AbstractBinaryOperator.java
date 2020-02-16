package expression;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.EvaluationException;
import expression.exceptions.OverflowException;

public abstract class AbstractBinaryOperator implements CommonExpression {
    protected final CommonExpression first, second;

    protected AbstractBinaryOperator(CommonExpression first, CommonExpression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        String res = "";
        try {
            res = "(" + first.toString() + this.getSign() + second.toString() + ")";
        } catch (NullPointerException e) {
            return "WrongSymbolException";
        }
        return res;
    }


    private boolean check(CommonExpression expression) {
        return this.getPriority() < expression.getPriority();
    }

    private boolean checkNext() {
        return check(second) || this.getPriority() == second.getPriority() &&
                (!this.isCommutative() || !second.isCommutative());
    }

    private void applyBrackets(StringBuilder result, CommonExpression expression, boolean addBrackets) {
        if (addBrackets) {
            result.append('(').append((expression.toMiniString())).append(')');
        } else {
            result.append(expression.toMiniString());
        }
    }

    @Override
    public String toMiniString() {
        StringBuilder result = new StringBuilder();
        applyBrackets(result, first, check(first));
        result.append(this.getSign());
        applyBrackets(result, second, checkNext());
        return result.toString();
    }

    public abstract int evaluate(int x, int y) throws OverflowException, DivisionByZeroException, EvaluationException;

    @Override
    public int evaluate(int x, int y, int z) throws OverflowException, EvaluationException {
        return evaluate(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    @Override
    public int hashCode() {
        return (first.hashCode() * 227 + second.hashCode() * 47 + getClass().hashCode());
    }

    @Override
    public boolean equals(Object object) {
        if (object != null && getClass() == object.getClass()) {
            AbstractBinaryOperator casted = (AbstractBinaryOperator) object;
            return this.first.equals(casted.first)
                    && this.second.equals(casted.second);
        } else {
            return false;
        }
    }
}
