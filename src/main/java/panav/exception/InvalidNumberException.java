package panav.exception;


/**
 * Encapsulates the exception that occurs when the user tries to edit/delete a task in the list
 * but provides a task number which is either less than zero, or more than the total number
 * of tasks in the entire list.
 */
public class InvalidNumberException extends DukeException {


    public InvalidNumberException(String message) {
        super(message);
    }

}
