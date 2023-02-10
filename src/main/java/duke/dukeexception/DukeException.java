package duke.dukeexception;

/**
 * This class handles exceptions pertaining to Duke
 */
public class DukeException extends Exception {
    private String errorMsg;
    public DukeException(String message) {
        super(message);
        this.errorMsg = message;
    }

    @Override
    public String toString() {
        return this.errorMsg;
    }
}
