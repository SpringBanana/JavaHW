package expression;

public abstract class AbstractUnaryOperator implements CommonExpression {
    protected final CommonExpression operand;

    public AbstractUnaryOperator(CommonExpression operand) {
        this.operand = operand;
    }

    @Override
    public String toString() {
        return this.getSign() + "(" + operand.toString() + ")";
    }

    abstract public int calculate(int x);

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(operand.evaluate(x, y, z));
    }

    @Override
    public int hashCode() {
        return 421241 * operand.hashCode() + 31 * getSign().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            AbstractUnaryOperator castedObj = (AbstractUnaryOperator) obj;
            return  this.operand.equals(castedObj.operand);
        }
        return false;
    }
}
