package duke.exceptions;

/**
 * The error class indicating that an invalid date format was entered.
 */
public class DukeInvalidDateException extends DukeException {
    public DukeInvalidDateException() {
        super(
                "An invalid date was entered. Please use this format:\n" +
                        "      {day}/{month}/{year} {hour}{minute}\n" +
                        "      Example: 2/2/23 1200"
        );
    }
}
