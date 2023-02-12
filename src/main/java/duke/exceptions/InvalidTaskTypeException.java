package duke.exceptions;

/**
 * InvalidTaskTypeException represents an exception if
 * no such task type exists.
 */
public class InvalidTaskTypeException extends Exception {

    /**
     * Creates a InvalidTaskTypeException
     */
    public InvalidTaskTypeException() {
        super("OOPS!!! I'm sorry, but I don't know what that task type means :-("
                + "\n" + "task types are : todo, deadline, event");
    }
}
