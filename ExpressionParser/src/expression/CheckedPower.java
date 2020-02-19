package expression;

import expression.exceptions.EvaluationException;
import expression.exceptions.MathException;

public class CheckedPower extends AbstractBinaryOperator {
    public CheckedPower(CommonExpression x, CommonExpression y) {
        super(x, y);
    }

    @Override
    public int evaluate(int x, int y) throws EvaluationException {
        if (x == 0 && y == 0 || y < 0) {
            throw new MathException("Integer overflow");
        }
        int result = 1;
        while (y != 0) {
            if (y % 2 == 1) {
                CheckedMultiply.checkMulOverflow(result, x);
                result = result * x;
                y--;
            }
            y /= 2;
            if (y == 0) {
                break;
            }
            CheckedMultiply.checkMulOverflow(x, x);
            x *= x;
        }
        return result;
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
        return "**";
    }

    @Override
    public String toString() {
        return "power" + '(' + this.first.toString() + ", " + this.second.toString() + ')';
    }

}
