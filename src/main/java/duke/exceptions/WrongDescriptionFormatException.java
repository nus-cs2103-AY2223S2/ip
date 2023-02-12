package duke.exceptions;

/**
 * This exception indicates that the description is not of required format.
 */
public class WrongDescriptionFormatException extends Exception {
	public WrongDescriptionFormatException() {
		super("Your description does not meet the requirements. Please try again.");
	}
}
