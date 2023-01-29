package duke.exception;

public class DukeTaskNotFoundException extends DukeException {
    public DukeTaskNotFoundException(String message) {
        super(message);
    }

    public String toString() {
        return "OOPS! " + super.getMessage();
    }
}
