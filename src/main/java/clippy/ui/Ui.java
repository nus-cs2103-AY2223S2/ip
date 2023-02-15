package clippy.ui;

/**
 * An abstraction encapsulating the user input/output interfaces.
 *
 * @author chunzkok
 */
public interface Ui {

    /**
     * Prints a given string with Clippy's personal style.
     *
     * @param output The string to be printed.
     */
    public void prettyPrint(String output);

    /**
     * Prints a given string with a more formal display.
     * This is used to display system messages.
     *
     * @param output The string to be printed.
     */
    public void systemPrint(String output);

    /**
     * Stops program execution gracefully.
     */
    public void exit();
}
