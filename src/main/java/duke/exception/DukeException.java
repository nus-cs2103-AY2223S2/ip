package duke.exception;
public class DukeException extends Exception {
    String errorMessage;

    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
