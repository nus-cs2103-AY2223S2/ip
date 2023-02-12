package duke;

/**
 * Exception for unrecognized command
 */
public class NotRecognizedCommandDukeException extends DukeException {
    private static String ERROR_MESSAGE = "The command could not be recognized!";

    /**
     * Constructor.
     */
    public NotRecognizedCommandDukeException() {
        super(wrapWithEncouragingWords(ERROR_MESSAGE));
    }
}
