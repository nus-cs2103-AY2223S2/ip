package exceptions;

/**
 * Exception class handles if an input is not an integer from the list
 */

public class InvalidNumberException extends DukeException {

    public InvalidNumberException() {
        super("Error...Please fill in an appropriate number from the list");
    }
}