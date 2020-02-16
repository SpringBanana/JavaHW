package expression;

import expression.exceptions.EvaluationException;
import expression.exceptions.OverflowException;

public abstract class AbstractUnaryOperator implements CommonExpression {
    protected final CommonExpression operand;

    public AbstractUnaryOperator(CommonExpression operand) {
        this.operand = operand;
    }

    @Override
    public String toString() {
        return this.toString() + "(" + operand.toString() + ")";
    }

    abstract public int calculate(int x) throws OverflowException;

    @Override
    public int evaluate(int x, int y, int z) throws OverflowException, EvaluationException {
        return calculate(operand.evaluate(x, y, z));
    }

    @Override
    public int hashCode() {
        return 227 * operand.hashCode() + 47 * getSign().hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object != null && getClass() == object.getClass()){
            AbstractUnaryOperator casted = (AbstractUnaryOperator) object;
            return this.operand.equals(casted.operand);
        } else {
            return false;
        }
    }
}