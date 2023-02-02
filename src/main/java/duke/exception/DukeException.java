package duke.exception;

/**
 * Exception for Duke chat bot.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(String.format("☹ OOPS!!! %s", message));
    }
}
