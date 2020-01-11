package expression.parser;

import expression.AbstractBinaryOperator;
import expression.CommonExpression;

public final class BinaryOperator {
    interface BinaryExpressionCreator {
        AbstractBinaryOperator create(CommonExpression first, CommonExpression second);
    }

    private final BinaryExpressionCreator creator;
    private final String sign;

    public BinaryOperator(BinaryExpressionCreator creator, String sign) {
        this.creator = creator;
        this.sign = sign;
    }

    public char getPrefix() {
        return sign.charAt(0);
    }

    public String getNextSymbols() {
        return sign.substring(1);
    }

    public AbstractBinaryOperator create(CommonExpression first, CommonExpression second) {
        return creator.create(first, second);
    }
}
