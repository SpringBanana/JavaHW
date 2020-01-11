package markup;

import java.util.List;

public class OrderedList extends AbstractList {
    public OrderedList(List<ListItem> elements) {
        super(elements);
    }

    @Override
    public void toTex(StringBuilder result) {
        toTex(result, "\\begin{enumerate}", "\\end{enumerate}");
    }

}
