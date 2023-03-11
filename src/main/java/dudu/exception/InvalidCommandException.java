package dudu.exception;

/**
 * Exception class on invalid command.
 */
public class InvalidCommandException extends DuduException {
    private String detail;
    /**
     * Constructor for invalid command exception.
     * @param msg Error message.
     */
    public InvalidCommandException(String msg) {
        super(msg);
        detail = "";
    }

    /**
     * Constructor for invalid command exception.
     * @param msg Error message
     * @param detail The detail error message to user.
     */
    public InvalidCommandException(String msg, String detail) {
        super(msg);
        this.detail = "\n" + detail;
    }

    @Override
    public String toString() {
        return "OOPS!!!\n I'm sorry, but I don't know what that means :-(" + detail;
    }
}
