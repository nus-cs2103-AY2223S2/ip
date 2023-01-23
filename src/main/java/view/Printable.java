package view;

/**
 * An interface for displaying data to a user view.
 */
public interface Printable {
    /**
     * Prints data to followed by a new line.
     *
     * @param out Data to be printed.
     */
    void println(String out);

    /**
     * Prints data to with indent, followed by a new line.
     *
     * @param out Data to be printed.
     */
    void printlnIndent(String out);

    /**
     * Prints error message followed by a new line.
     *
     * @param out Data to be printed.
     */
    void printlnError(String out);

    /**
     * Prints data to indented.
     *
     * @param out Data to be printed.
     */
    void printIndent(String out);
}
