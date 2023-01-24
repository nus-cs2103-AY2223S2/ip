package duke.exception;

/**
 * Constructs a new exception representing an invalid todo task input.
 */
public class InvalidTodoException extends Exception {

    public InvalidTodoException() {
        super("The description of a todo cannot be empty.\n");
    }
}

