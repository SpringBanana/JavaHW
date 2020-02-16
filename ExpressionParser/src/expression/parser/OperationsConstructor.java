package expression.parser;

import expression.AbstractBinaryOperator;
import expression.CommonExpression;

public final class OperationsConstructor {
    interface Constructor {
        AbstractBinaryOperator create(CommonExpression first, CommonExpression second);
    }

    private final Constructor constructor;
    private final String sign;

    public OperationsConstructor(Constructor creator, String sign) {
        this.constructor = creator;
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    public AbstractBinaryOperator create(CommonExpression first, CommonExpression second) {
        return constructor.create(first, second);
    }
}
