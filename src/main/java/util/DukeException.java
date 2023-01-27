package util;

/**
 * Custom exception class which throws a
 * custom error message.
 */
public class DukeException extends Exception {
    public DukeException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return String.format("Oh no! %s", super.getMessage());
    }
}
