package duke.exceptions;

public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return String.format("Whoops! %s", super.getMessage());
    }
}
