package shigure;

/**
 * A UI controller for Miki user interaction.
 */
public abstract class Ui {
    /**
     * Prints a line divider to the user interface.
     */
    public abstract void printDiv();

    /**
     * If automatic internal line dividers are enabled, prints a line divider.
     */
    public abstract void printAutoDiv();

    /**
     * Prints a block of output to the user interface, quoted from the User.
     *
     * @param s <code>String</code> to be printed.
     */
    public abstract void printUser(String s);

    /**
     * Prints a block of output to the user interface, quoted as Miki.
     *
     * @param s <code>String</code> to be printed.
     */
    public abstract void printMiki(String s);

    /**
     * Displays an array containing the <code>String</code> representations of tasks.
     *
     * @param tasks <code>String[]</code> to display.
     */
    public abstract void printTasks(String[] tasks);

    /**
     * Updates an existing task-list, if possible, with an array containing
     * the <code>String</code> representations of tasks.
     *
     * @param tasks <code>String[]</code> to update with.
     */
    public abstract void refreshTasks(String[] tasks);

    /**
     * Prints a welcome message to the user interface.
     */
    public abstract void printIntro();

    /**
     * Clears the user input section, if necessary.
     */
    public abstract void clearInput();

    /**
     * Closes the UI, if possible.
     */
    public abstract void close();

}
