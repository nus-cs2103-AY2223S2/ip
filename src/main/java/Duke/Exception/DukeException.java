package Duke.Exception;

/**
 * handles command exception.
 */
public class DukeException extends Exception {
    private String message;

    /**
     * Handles command exception.
     */
    public DukeException() {
        this.message = "bogus";
    }
}
