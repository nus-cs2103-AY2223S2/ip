package duke.exception;

/**
 * The DukeException class is a custom exception used to represent errors that occur
 * during the execution of the Duke chatbot application.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
