package duke;

/**
 * An exception class for the chatbot with the provided hints in the exception message.
 */
public class DukeException extends RuntimeException {
    /**
     * A Datetime Format Exception for when the user keys in a datetime format that is not recognised by the chatbot
     * and provides a hint on the structure of the date to be input.
     */
    public static final DukeException DATETIME_FORMAT = new DukeException(
            "Only datetime format of 2023-01-01 is accepted");

    /**
     * Constructor for a new exception that comprised of the hint to be displayed.
     *
     * @param message The hint to be displayed
     */
    public DukeException(String message) {
        super(message);
    }
}
