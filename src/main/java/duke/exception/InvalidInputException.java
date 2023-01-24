package duke.exception;

/**
 * Constructs a new exception representing an invalid task input.
 */
public class InvalidInputException extends Exception {

    public InvalidInputException() {
        super("I'm sorry, but I don't know what that means :-(\n");
    }
}
