package expression.parser;

import expression.TripleExpression;
import expression.exceptions.EvaluationException;
import expression.exceptions.ParsingException;

public class Test {
    public static void main(String[] args) throws ParsingException, EvaluationException {
        TripleExpression test = new ExpressionParser().parse("((2072933853) // (1009340418)) - ((z) / (y))");
        System.out.println(test.evaluate(-170439996, 1234510033, -1182702066));
    }
}
