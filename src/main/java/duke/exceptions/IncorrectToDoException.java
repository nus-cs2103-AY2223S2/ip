package duke.exceptions;

/**
 * Exception is thrown when user enters an empty description for todo
 */
public class IncorrectToDoException extends NeroException {

    public IncorrectToDoException() {
        super("Description cannot be empty!!!");
    }
}
