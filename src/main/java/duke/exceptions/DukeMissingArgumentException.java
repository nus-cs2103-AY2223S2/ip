package duke.exceptions;
public class DukeMissingArgumentException extends DukeException {

    public DukeMissingArgumentException(String message) {
        super(message);
    }

    public DukeMissingArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
