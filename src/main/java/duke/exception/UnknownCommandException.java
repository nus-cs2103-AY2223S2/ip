package duke.exception;

/** An exception thrown when a user issues a command that is not understood. */
public class UnknownCommandException extends DukeException {

    /**
     * Initializes an UnknownCommandException caused by an unknown user command.
     * 
     * @param cmd The command given by the user that cannot be parsed
     */
    public UnknownCommandException(String cmd) {
        super(cmd);
    }

    @Override
    public String getExceptionName() {
        return "Unknown Command";
    }
}
