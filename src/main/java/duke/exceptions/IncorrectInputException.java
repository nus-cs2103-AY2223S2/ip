package duke.exceptions;

/**
 * Exception is thrown when user keys in an unknown command
 */
public class IncorrectInputException extends NeroException {

    public IncorrectInputException() {
        super("Wrong input!! Command not found!!");
    }
}
