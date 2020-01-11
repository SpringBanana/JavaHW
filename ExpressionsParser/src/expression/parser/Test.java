package expression.parser;

import expression.*;

public class Test {
    public static void main(String[] args) {
        CommonExpression Parse = new ExpressionParser().parse("--1---5");
        System.out.println(Parse.toString());
    }
}
