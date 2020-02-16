package expression;

import java.util.InputMismatchException;
import java.util.Objects;

public class Variable implements CommonExpression {
    private final String name;

    public Variable(String name) {
        this.name = name;
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
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Variable) {
            Variable casted = (Variable) obj;
            return Objects.equals(this.name, casted.name);
        } else {
            return false;
        }
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                throw new InputMismatchException("Unknown variable");
        }
    }
}
