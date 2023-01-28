package duke.exceptions;

/**
 * Represents an exception thrown by Duke.
 */
public class DukeException extends Throwable {

    public DukeException() {
        super();
    }

    /**
     * Returns string representation of the Duke Exception.
     *
     * @return String representation of the Duke Exception.
     */
    @Override
    public String toString() {
        return "Duke Exception: Something went wrong! Couldn't really pinpoint it though D:";
    }

}
