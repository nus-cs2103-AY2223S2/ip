package duke;

/**
 * Exception for incomplete command
 */
public class IncompleteCommandDukeException extends DukeException {
    private static String ERROR_MESSAGE = "The command seems incomplete!";

    /**
     * Constructor.
     */
    public IncompleteCommandDukeException() {
        super(wrapWithEncouragingWords(ERROR_MESSAGE));
    }

    /**
     * Another constructor when there is additional information
     *
     * @param error the error message to display
     */
    public IncompleteCommandDukeException(String error) {
        super(wrapWithEncouragingWords(String.format("%s\n%s", ERROR_MESSAGE, error)));
    }
}
