package exception;

/**
 * Exception to handle the case where the user input is ""
 */
public class MissingArgumentException extends DukeException{
    public MissingArgumentException(String err) {
        super(err);
    }
}
