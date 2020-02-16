package expression.parser;

public interface Source {
    boolean hasNext();

    char next();

    void back();

    String getFull();

    int getPos();

    Exception error(final String message);
}
