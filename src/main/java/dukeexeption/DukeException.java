package dukeexeption;

public class DukeException extends RuntimeException {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! " + super.getMessage();
    }
}
