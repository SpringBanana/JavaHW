package expression;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;

public strictfp class CheckedDivide extends AbstractBinaryOperator {
    public CheckedDivide(CommonExpression x, CommonExpression y) {
        super(x, y);
    }

    @Override
    public int evaluate(int x, int y) throws OverflowException, DivisionByZeroException {
        if ((x == Integer.MIN_VALUE) && (y == -1)) {
            throw new OverflowException(" when divide " + x + " / " + y);
        }
        if (y == 0) {
            throw new DivisionByZeroException();
        }
        return x / y;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public String getSign() {
        return " / ";
    }

    @Override
    public boolean isCommutative() {
        return false;
    }
}