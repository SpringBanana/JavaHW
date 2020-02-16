package expression;

import expression.exceptions.OverflowException;

public strictfp class CheckedSubtract extends AbstractBinaryOperator {
    public CheckedSubtract(CommonExpression x, CommonExpression y) {
        super(x, y);
    }


    @Override
    public int evaluate(int x, int y) throws OverflowException {
        if (y > 0 ? x < Integer.MIN_VALUE + y
                : x > Integer.MAX_VALUE + y) {
            throw new OverflowException();
        }
        return x - y;
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public boolean isCommutative() {
        return false;
    }

    @Override
    public String getSign() {
        return " - ";
    }
}