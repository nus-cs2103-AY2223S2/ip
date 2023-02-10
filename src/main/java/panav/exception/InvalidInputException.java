
package panav.exception;

/**
 * Encapsulates the exception that occurs when the user enters a command which is not
 * recognised and not included in the list of possible commands.
 */
public class InvalidInputException extends DukeException {

    public InvalidInputException(String message) {
        super(message);
    }



}
