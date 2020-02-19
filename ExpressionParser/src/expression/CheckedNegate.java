package expression;

import expression.exceptions.OverflowException;

public strictfp class CheckedNegate extends AbstractUnaryOperator {
    public CheckedNegate(CommonExpression x) {
        super(x);
    }


    @Override
    public int calculate(int x) throws OverflowException {
        if (x == Integer.MIN_VALUE)
            throw new OverflowException(" " + x);
        return -x;
    }

    @Override
    public int getPriority() {
        return -1;
    }

    @Override
    public String toMiniString() {
        return this.toString();
    }

    @Override
    public String getSign() {
        return "-";
    }

    @Override
    public String toString() {
        return "-" + this.operand.toString();
    }
}