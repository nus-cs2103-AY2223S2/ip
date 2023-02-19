package duke;

/**
 * Exception for date format
 */
public class DateFormatDukeException extends DukeException {
    private static final String NUMBER_FORMAT_MESSAGE = "The date could not be parsed!";

    /**
     * Constructor.
     */
    public DateFormatDukeException() {
        super(wrapWithEncouragingWords(NUMBER_FORMAT_MESSAGE));
    }
}
