package markup;

import java.util.List;

abstract class AbstractList implements InList {
    private List<ListItem> elements;

    AbstractList(List<ListItem> elements) {
        this.elements = elements;
    }

    public void toTex(StringBuilder result, String leftBorder, String rightBorder) {
        result.append(leftBorder);
        for (ListItem element : elements) {
            element.toTex(result);
        }
        result.append(rightBorder);
    }
}
