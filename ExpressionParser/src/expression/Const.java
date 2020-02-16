package expression;

import java.util.Objects;

public class Const implements CommonExpression {
    final int value;

    public Const(int value) {
        this.value = value;
    }

    @Override
    public int getPriority() {
        return -1;
    }

    @Override
    public boolean isCommutative() {
        return false;
    }

    @Override
    public String toMiniString() {
        return this.toString();
    }

    @Override
    public String getSign() {
        return null;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Const) {
            Const casted = (Const) obj;
            return Objects.equals(this.value, casted.value);
        } else {
            return false;
        }
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value;
    }

}
