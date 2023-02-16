package genie.exception;

/**
 * Handles exceptions where user input is empty.
 */
public class EmptyInputException extends GenieException {
    public EmptyInputException(String s) {
        super("Uh oh! The description of " + s + " cannot be empty.");
    }
}
