package duke;

/**
 * Exception for number format
 */
public class NumberFormatDukeException extends DukeException {
    static String NUMBER_FORMAT_MESSAGE = "Please specify a number instead.";

    /**
     * Constructor.
     */
    public NumberFormatDukeException() {
        super(NUMBER_FORMAT_MESSAGE);
    }
}
