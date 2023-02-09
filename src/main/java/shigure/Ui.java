package shigure;

/**
 * A UI controller for Miki interactive command-line I/O.
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
     * Prints a line of output to the user interface,
     * formatted with indentation for clarity.
     *
     * @param s <code>String</code> to be printed.
     */
    //public abstract void print(String s);
    public abstract void printUser(String s);

    public abstract void printMiki(String s);

    public abstract void printTasks(String[] tasks);

    public abstract void refreshTasks(String[] tasks);

    /**
     * Prints a welcome message to the user interface.
     */
    public abstract void printIntro();

    public abstract void clearInput();

    public abstract void close();

}
