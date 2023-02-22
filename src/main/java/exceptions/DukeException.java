package exceptions;


// Exceptions should be packaged with the classes that can throw those exceptions?
public class DukeException extends RuntimeException {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
