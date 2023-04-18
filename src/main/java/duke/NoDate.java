package duke;

/**
 * Represents an Exception due to no date input for deadline tasks.
 */
public class NoDate extends Exception {
    public NoDate() {
        super("No deadline date found. Please input a date in this format: yyyy-mm-dd");
    }
}
