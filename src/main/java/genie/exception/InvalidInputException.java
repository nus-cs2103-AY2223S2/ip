package genie.exception;

/**
 * Handles exceptions where user input is invalid.
 */
public class InvalidInputException extends GenieException {
    public InvalidInputException() {
        super("Hmm, I'm not quite sure what that means... Enter the 'help' command if you need more guidance!");
    }
}