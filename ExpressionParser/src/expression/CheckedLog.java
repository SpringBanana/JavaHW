package expression;

import expression.exceptions.EvaluationException;
import expression.exceptions.MathException;
import expression.exceptions.OverflowException;

public class CheckedLog extends AbstractBinaryOperator {

    public CheckedLog(CommonExpression x, CommonExpression y) {
        super(x, y);
    }

    @Override
    public int evaluate(int x, int y) throws EvaluationException {
        if (x <= 0 || y <= 0 || y == 1)
            throw new MathException("Integer overflow");
        int i = 1;
        int result = 0;
        while (i <= x) {
            try {
                CheckedMultiply.checkMulOverflow(i,y);
            } catch (OverflowException e) {
                return result;
            }
            i *= y;
            result++;
        }
        return result - 1;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean isCommutative() {
        return false;
    }

    @Override
    public String getSign() {
        return "//";
    }

    @Override
    public String toString() {
        return "log" + '(' + this.first.toString() + ", " + this.second.toString() + ')';
    }
}
