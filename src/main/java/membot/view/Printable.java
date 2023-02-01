package membot.view;

/**
 * An interface for displaying data to a user view.
 */
public interface Printable {
    /**
     * Prints an array of data to <code>stdout</code>. Data is prefixxed with
     * running index starting from 1. For example:
     * 1. Data 1
     * 2. Data 2
     * ...
     *
     * @param args Array of data to be printed out.
     */
    void listPrint(String... args);

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
