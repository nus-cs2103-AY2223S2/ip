package exception;

/**
 * Represents exceptions caused by invalid input
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "OOPS!!! " + getMessage();
    }
}
