/**
 * Class encapsulates an exception which occurs when the user wants to manipulate
 * his list using commands such as 'delete', 'mark' etc. but inputs an index number
 * which doesn't exist in the list.
 */
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
