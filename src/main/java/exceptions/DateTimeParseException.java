package exceptions;

/**
 * Exception thrown when the user inputs an invalid date or time.
 */
public class DateTimeParseException extends DukeException {
	public DateTimeParseException(String errorMessage) {
		super(errorMessage);
	}
}
