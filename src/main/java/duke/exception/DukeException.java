package duke.exception;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(String.format("Do you take me for a FOOL?!\n%s", message));
    }
}
