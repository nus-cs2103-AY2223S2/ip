package duke.exceptions;

/**
 * This exception indicates that the date entered is of wrong format.
 */
public class WrongDateFormatException extends Exception {
	public WrongDateFormatException() {
		super("Please enter date in the format of MM-DD-YYYY.");
	}

}
