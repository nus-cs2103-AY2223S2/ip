package exception;

/**
 * Exception to handle the case where the user input is missing arguments
 */
public class MissingArgumentException extends DukeException{
    public MissingArgumentException(String err) {
        super(err);
    }
}
