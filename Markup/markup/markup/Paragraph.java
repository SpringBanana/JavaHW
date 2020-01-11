package markup;

import java.util.List;

public class Paragraph extends AbstractElement implements InList {
    public Paragraph(List<InParagraph> elements) {
        super(elements);
    }

    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "");
    }

    public void toTex(StringBuilder result) {
        toTex(result, "", "");
    }
}
