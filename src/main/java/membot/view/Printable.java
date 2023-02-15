package membot.view;

/**
 * An interface for displaying data to a user view.
 */
public interface Printable {
    /**
     * Prints data to followed by a new line.
     *
     * @param out Data to be printed.
     */
    void println(String... out);

    /**
     * Prints error message followed by a new line.
     *
     * @param out Data to be printed.
     */
    void printlnError(String... out);

    void printSeparator();
}
