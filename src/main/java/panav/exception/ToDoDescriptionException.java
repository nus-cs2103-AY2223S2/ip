package panav.exception;


/**
 * Encapsulates the exception that occurs when the user types in a todo command but does not
 * provide any description.
 */
public class ToDoDescriptionException extends DukeException {

    public ToDoDescriptionException(String message) {
        super(message);
    }


}
