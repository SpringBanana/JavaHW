package expression;

import expression.exceptions.OverflowException;

public strictfp class CheckedMultiply extends AbstractBinaryOperator {
    public CheckedMultiply(CommonExpression x, CommonExpression y) {
        super(x, y);
    }



    @Override
    public int evaluate(int x, int y) throws OverflowException {
        checkMulOverflow(x, y);
        return x * y;
    }

    static void checkMulOverflow(int x, int y) {
        if (y > 0 ? x > Integer.MAX_VALUE/y
                || x < Integer.MIN_VALUE/y
                : (y < -1 ? x > Integer.MIN_VALUE/y
                || x < Integer.MAX_VALUE/y
                : y == -1
                && x == Integer.MIN_VALUE) ) {
            throw new OverflowException();
        }
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public String getSign() {
        return " * ";
    }
}