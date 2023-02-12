package duke.exceptions;


/**
 * This exception indicates that there is no description followed by command.
 */
public class NoDescriptionException extends Exception {

	public NoDescriptionException(String command) {
		super(String.format("The description of a %S cannot be empty.", command));
	}

}