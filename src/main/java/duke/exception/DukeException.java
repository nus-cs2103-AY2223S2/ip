package duke.exception;

/**
 * Simple class to represent Duke exceptions.
 */
public class DukeException extends Exception {
    public DukeException(String str) {
        super(str);
    }

    @Override
    public String toString() {
        return "ERROR! " + this.getMessage();
    }
}
