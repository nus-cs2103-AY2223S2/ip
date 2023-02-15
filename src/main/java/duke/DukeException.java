package duke;

/**
 * A custom checked exception Duke objects can throw when user commands do not make sense or conform to formats and
 * standards.
 */
public class DukeException extends Exception {
    private String message;

    /**
     * The constructor for DukeException.
     * @param message The error message describing the source of error.
     */
    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Gets error message describing the problem.
     * @return The error message.
     */
    public String getMessage() {
        return this.message;
    }
}
