package duke;

/**
 * Exception used in Duke.
 */
public class DukeException extends Exception {
    private String message;

    /**
     * Class constructor of DukeException.
     * @param message the exception details
     */
    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Returns string representation of DukeException.
     * @return String representation of DukeException
     */
    @Override
    public String toString() {
        return "OOPS!!! " + this.message;
    }
}
