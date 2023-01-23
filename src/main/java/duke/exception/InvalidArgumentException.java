package duke.exception;

/** An exception thrown when a user issues a command with invalid arguments. */
public class InvalidArgumentException extends DukeException {
    /** The command causing the InvalidArgumentException */
    private String command;

    /**
     * Initializes an InvalidArgumentException that was caused by a command.
     * 
     * @param command The command causing the InvalidArgumentException
     */
    public InvalidArgumentException(String command) {
        super("Arguments are of the wrong type or format");
        this.command = command;
    }

    @Override
    public String getExceptionName() {
        String name = String.format("Invalid Argument(s) for %s", this.command);
        return name;
    }
}
