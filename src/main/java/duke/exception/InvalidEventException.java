package duke.exception;

/**
 * A class to represent an invalid Event instatiation.
 */
public class InvalidEventException extends Error {
    public InvalidEventException() {
        super("☹ OOPS!!! from date should be before to date!");
    }
}
