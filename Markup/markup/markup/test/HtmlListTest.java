package markup.test;


import markup.OrderedList;
import markup.Paragraph;
import markup.UnorderedList;

import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HtmlListTest extends ListTest {
    private static final Map<String, String> HTML = Map.of();

    @Override
    protected void test(final Paragraph paragraph, final String expected) {
        test(paragraph::toTex, expected, HTML);
    }

    protected void test(UnorderedList list, final String expected) {
        test(list::toTex, expected, HTML);
    }

    protected void test(OrderedList list, final String expected) {
        test(list::toTex, expected, HTML);
    }

    public static void main(String[] args) {
        new HtmlListTest().run();
    }
}
