package expression.parser;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */

public class BaseParser {
    private final Source source;
    protected char ch;

    protected BaseParser(final Source source) {
        this.source = source;
        nextChar();
    }

    protected void nextChar() {
        ch = source.hasNext() ? source.next() : '\0';
    }

    protected boolean test(char expected) {
        if (ch == expected) {
            nextChar();
            return true;
        }
        return false;
    }

    protected void expect(final char c) {
        if (ch != c) {
        }
        nextChar();
    }

    protected String testNumber() {
        if (between('0', '9')) {
            StringBuilder result = new StringBuilder();
            result.append(ch);
            nextChar();
            while (between('0', '9')) {
                result.append(ch);
                nextChar();
            }
            return result.toString();
        }
        return null;
    }

    protected void expect(final String value) {
        for (char c : value.toCharArray()) {
            expect(c);
        }
    }

        protected void skipWhitespace() {
        while (test(' ') || test('\r') || test('\n') || test('\t')) {
        }
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }
}
