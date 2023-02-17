package duke.exception;

/**
 * Exception thrown when command is not recognized
 */
public class WrongCommandException extends Exception {
    public WrongCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

}
