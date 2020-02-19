package expression;

import expression.exceptions.*;

public strictfp class CheckedAdd extends AbstractBinaryOperator {
    public CheckedAdd(CommonExpression x, CommonExpression y) {
        super(x, y);
    }


    @Override
    public int evaluate(int x, int y) throws OverflowException {
        if (y > 0 ? x > Integer.MAX_VALUE - y
                : x < Integer.MIN_VALUE - y) {
            throw new OverflowException(" when add " + x + " - " + y);
        }
        return x + y;
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public String getSign() {
        return " + ";
    }
}