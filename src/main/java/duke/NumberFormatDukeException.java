package duke;

/**
 * Exception for number format
 */
public class NumberFormatDukeException extends DukeException {
    private static String ERROR_MESSAGE = "Please specify a number instead.";

    /**
     * Constructor.
     */
    public NumberFormatDukeException() {
        super(wrapWithEncouragingWords(ERROR_MESSAGE));
    }
}
