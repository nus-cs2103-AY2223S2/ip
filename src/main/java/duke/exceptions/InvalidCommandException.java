package duke.exceptions;

/**
 * This exception indicates an invalid command.
 */
public class InvalidCommandException extends Exception {
	public InvalidCommandException() {
		super("There is no such command.");
	}
}
