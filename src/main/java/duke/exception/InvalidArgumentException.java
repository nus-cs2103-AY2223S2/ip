package duke.exception;

/**
 * A class to represent an invalid argument.
 */
public class InvalidArgumentException extends Error {
    public InvalidArgumentException() {
        super("☹ OOPS!!! I'm sorry, but I don't understand your argument :-(");
    }
}
