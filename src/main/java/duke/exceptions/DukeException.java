package duke.exceptions;

public class DukeException extends Exception {

    public DukeException() {
        super("error");
    }

    public DukeException(String message) {
        super(message);
    }
}
