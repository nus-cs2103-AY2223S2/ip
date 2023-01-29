package iris.exception;

/**
 * Wraps all errors encountered while chatting and provides a custom message
 */
public class IrisException extends Exception {
    private final String message;
    public IrisException() {
        this.message = "OOPS!!! Something went wrong." + " Type \"help\" to see the commands.";
    }

    public IrisException(String message) {
        this.message = "OOPS!!! " + message + " Type \"help\" to see the commands.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
