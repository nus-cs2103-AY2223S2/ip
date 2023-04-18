package seedu.duke.exceptions;


// Exceptions should be packaged with the classes that can throw those duke.exceptions?
public class DukeException extends RuntimeException {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
