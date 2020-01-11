package expression.parser;

import expression.*;
import expression.Unary.Digits;
import expression.Unary.Reverse;
import expression.bitwise.LeftShift;
import expression.bitwise.RightShift;

import java.util.List;

public class ExpressionParser implements Parser {

    @Override
    public CommonExpression parse(String expression) {
        return new InnerParser(new StringSource(expression)).parse(0);
    }

    private final static List<List<BinaryOperator>> binaryOperators = List.of(
            List.of(op(LeftShift::new, "<<"), op(RightShift::new, ">>")),
            List.of(op(Add::new, "+"), op(Subtract::new, "-")),
            List.of(op(Multiply::new, "*"), op(Divide::new, "/"))
    );


    private static BinaryOperator op(BinaryOperator.BinaryExpressionCreator consturctor, String sign) {
        return new BinaryOperator(consturctor, sign);
    }

    private final static List<UnaryOperator> unaryOperators = List.of(
            new UnaryOperator(Digits::new, "digits"),
            new UnaryOperator(Reverse::new, "reverse")
    );

    private final static List<String> Variables = List.of(
            "x", "y", "z"
    );

    private static class InnerParser extends BaseParser {

        protected InnerParser(Source source) {
            super(source);
        }

        private CommonExpression parse(int type) {
            if (type == binaryOperators.size()) {
                return parsePrimal();
            }
            CommonExpression left = parse(type + 1);
            List<BinaryOperator> level = binaryOperators.get(type);
            boolean found;
            do {
                found = false;
                skipWhitespace();
                for (BinaryOperator op : level) {
                    if (test(op.getPrefix())) {
                        expect(op.getNextSymbols());
                        left = op.create(left, parse(type + 1));
                        found = true;
                    }
                }
            } while (found);
            return left;
        }

        private CommonExpression parsePrimal() {
            skipWhitespace();
            String number;
            if (test('-')) {
                if ((number = testNumber()) != null) {
                    return new Const(Integer.parseInt("-" + number));
                } else {
                    return new Negate(parsePrimal());
                }
            } else if ((number = testNumber()) != null) {
                return new Const(Integer.parseInt(number));
            } else if (test('(')) {
                CommonExpression result = parse(0);
                expect(')');
                return result;
            } else {
                for (UnaryOperator unaryOperator : unaryOperators) {
                    if (test(unaryOperator.getPrefix())) {
                        expect(unaryOperator.getContinuation());
                        return unaryOperator.create(parsePrimal());
                    }
                }
                for (String name : Variables) {
                    if (test(name.charAt(0))) {
                        expect(name.substring(1));
                        return new Variable(name);
                    }
                }
            }
            return null;
        }
    }
}
