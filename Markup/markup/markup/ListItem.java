package markup;

import java.util.List;

public class ListItem implements InList {
    private List<InList> elements;

    public ListItem(List<InList> elements) {
        this.elements = elements;
    }

    @Override
    public void toTex(StringBuilder result) {
        result.append("\\item ");
        for (InList element : elements) {
            element.toTex(result);
        }
    }
}
