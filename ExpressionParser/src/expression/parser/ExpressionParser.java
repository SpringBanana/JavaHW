package expression.parser;

import expression.*;
import expression.exceptions.OverflowException;
import expression.exceptions.ParanthesisException;
import expression.exceptions.ParsingException;
import expression.exceptions.WrongSymbolException;

import java.util.List;
import java.util.function.BiFunction;

public class ExpressionParser implements Parser {

    @Override
    public TripleExpression parse(String expression) throws ParsingException {
        TripleExpression result;
        ParseExpressions parserExpression = new ParseExpressions(new StringSource(expression + '\0'));
        result = parserExpression.parse(0);
        if (parserExpression.test(')')) {
            throw new ParanthesisException("opening", 1, expression);
        }
        if (parserExpression.between('0', '9')) {
            throw new WrongSymbolException("Whitespaces in const", expression.length() - 2, expression);
        }
        if (parserExpression.hasNext()) {
            throw new WrongSymbolException(" ", expression.length(), expression);
        }
        return result;
    }


    private static OperationsConstructor operator(OperationsConstructor.Constructor constructor, String sign) {
        return new OperationsConstructor(constructor, sign);
    }


    private final static List<String> VARIABLES = List.of(
            "x", "y", "z"
    );


    private final static List<List<OperationsConstructor>> BIN_LEVEL = List.of(
            List.of(operator(CheckedAdd::new, "+"), operator(CheckedSubtract::new, "-")),
            List.of(operator(CheckedMultiply::new, "*"), operator(CheckedDivide::new, "/")),
            List.of(operator(CheckedPower::new, "**"), operator(CheckedLog::new, "//"))

    );

    private static class ParseExpressions extends BaseParser {

        protected ParseExpressions(Source source) {
            super(source);
        }

        private CommonExpression parseElement() throws ParsingException {
            skipWhitespace();
            String number;
            if (test('-')) {
                if ((number = testNumber()) != null) {
                    try {
                        return new Const(Integer.parseInt("-" + number));
                    } catch (NumberFormatException e) {
                        throw new OverflowException(" in " + '-' + number);
                    }
                } else {
                    return new CheckedNegate(parseElement());
                }
            } else if ((number = testNumber()) != null) {
                try {
                    return new Const(Integer.parseInt(number));
                } catch (NumberFormatException e) {
                    throw new OverflowException(" in " + number);
                }
            } else if (test('(')) {
                CommonExpression result = parse(0);
                skipWhitespace();
                if (!test(')')) {
                    throw new ParanthesisException("closing", getPos(), getExpression());
                }
                return result;
            } else {
                for (String name : VARIABLES) {
                    if (test(name.charAt(0))) {
                        return new Variable(name);
                    }
                }
            }
            throw new WrongSymbolException("No argument exception ", getPos(), getExpression());
        }

        private CommonExpression parse(int level) throws ParsingException {
            if (level == BIN_LEVEL.size()) {
                return parseElement();
            }
            CommonExpression left = parse(level + 1);
            List<OperationsConstructor> depth = BIN_LEVEL.get(level);
            boolean isFound;
            do {
                isFound = false;
                skipWhitespace();
                for (OperationsConstructor operator : depth) {
                    if (expect(operator.getSign())) {
                        CommonExpression next = parse(level + 1);
                        left = operator.create(left, next);
                        isFound = true;
                    }
                }
            } while (isFound);
            if (left != null) {
                return left;
            } else {
                throw new WrongSymbolException("No argument exception ", getPos(), getExpression());
            }
        }

        @Override
        public boolean hasNext() {
            return super.hasNext();
        }
    }
}
