package duke.exception;

/**
 * Exception for data handling.
 */
public class DukeDataException extends DukeException {
    @Override
    public String toString() {
        return String.format("%s Error in data read..", super.toString());
    }
}
