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

    protected boolean expect(final char c) {
        if (ch != c) {
            return false;
        }
        nextChar();
        return true;
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

    protected boolean expect(final String value) {
        if (value.length() > 1) {
            if (ch == value.charAt(0)) {
                if (source.next() == value.charAt(1)) {
                    nextChar();
                    return true;
                } else {
                    source.back();
                }
            }
        } else {
            if (expect(value.charAt(0)))
                return true;
        }
        return false;
    }

    protected void skipWhitespace() {
        while (test(' ') || test('\r') || test('\n') || test('\t')) {
        }
    }

    protected boolean hasNext() {
        return source.hasNext();
    }


    protected Exception error(final String message) {
        return source.error(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }

    public int getPos() {
        return source.getPos();
    }

    public String getExpression() {
        return source.getFull();
    }
}
