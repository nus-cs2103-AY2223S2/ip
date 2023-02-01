package dudu.exception;

/**
 * Exception class on invalid command.
 */
public class InvalidCommandException extends DuduException {
    /**
     * Constructor for invalid command exception.
     * @param msg Error message.
     */
    public InvalidCommandException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "OOPS!!!\n I'm sorry, but I don't know what that means :-(";
    }
}
