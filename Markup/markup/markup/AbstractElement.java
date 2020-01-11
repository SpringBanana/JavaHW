package markup;

import java.util.List;

abstract class AbstractElement {
    private List<InParagraph> elements;

    AbstractElement(List<InParagraph> elements) {
        this.elements = elements;
    }

    void toMarkdown(StringBuilder result, String border) {
        result.append(border);
        for (InParagraph element : elements) {
            element.toMarkdown(result);
        }
        result.append(border);
    }

    protected void toTex(StringBuilder result, String leftBorder, String rightBorder) {
        result.append(leftBorder);
        for (InParagraph element : elements) {
            element.toTex(result);
        }
        result.append(rightBorder);
    }
}
