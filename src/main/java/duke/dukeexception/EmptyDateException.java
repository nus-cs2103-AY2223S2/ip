package duke.dukeexception;

/**
 * This class handles exceptions pertaining to empty dates in commands that require dates.
 */
public class EmptyDateException extends EmptyFieldException {
    private static final String MESSAGE = "Your command has an empty date!! :(";

    /**
     * Creates an EmptyDateException object using its default message.
     */
    public EmptyDateException() {
        super(EmptyDateException.MESSAGE);
    }
}
