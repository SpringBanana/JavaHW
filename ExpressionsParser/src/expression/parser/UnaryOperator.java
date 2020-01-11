package expression.parser;

import expression.AbstractUnaryOperator;
import expression.CommonExpression;

public final class UnaryOperator {
    interface UnaryExpressionCreator {
        AbstractUnaryOperator create(CommonExpression operand);
    }

    private final UnaryExpressionCreator creator;
    private final String sign;

    public UnaryOperator(UnaryExpressionCreator creator, String sign) {
        this.creator = creator;
        this.sign = sign;
    }

    public char getPrefix() {
        return sign.charAt(0);
    }

    public String getContinuation() {
        return sign.substring(1);
    }

    public AbstractUnaryOperator create(CommonExpression operand) {
        return creator.create(operand);
    }
}
