package duke;

/**
 * Represents an Exception due to incorrect input number for mark, unmark and delete.
 */
public class OutOfBounds extends Exception {
    public OutOfBounds(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "OOPS!!!" + getMessage();
    }
}