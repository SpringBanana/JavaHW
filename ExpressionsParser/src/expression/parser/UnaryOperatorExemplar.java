package expression.parser;

import expression.AbstractUnaryOperator;
import expression.CommonExpression;

public final class UnaryOperatorExemplar {
    interface UnOpsCreator {
        AbstractUnaryOperator create(CommonExpression operand);
    }

    private final UnOpsCreator creator;
    private final String sign;

    public UnaryOperatorExemplar(UnOpsCreator creator, String sign) {
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
