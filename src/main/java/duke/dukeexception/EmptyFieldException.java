package duke.dukeexception;

/**
 * This class handles exceptions pertaining to empty fields in commands.
 */
public class EmptyFieldException extends InvalidCommandException {
    private static final String MESSAGE = "Your command has an empty field!! :(";

    /**
     * Creates an EmptyFieldException object using its default message.
     */
    public EmptyFieldException() {
        this(EmptyFieldException.MESSAGE);
    }

    /**
     * Creates an EmptyFieldException object.
     *
     * @param message The error message of this exception.
     */
    protected EmptyFieldException(String message) {
        super(message);
    }
}
