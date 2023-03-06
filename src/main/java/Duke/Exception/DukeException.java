package Duke.Exception;

public class DukeException extends Exception {
    private String message;

    /**
     * Handles command exception.
     */
    public DukeException() {
        this.message = "bogus";
    }
}
