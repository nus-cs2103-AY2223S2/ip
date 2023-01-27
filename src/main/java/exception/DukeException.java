package exception;

public class DukeException extends Exception {
    static final String LINES = "\t____________________________________________________________\n";

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! " + getMessage();
    }
}
