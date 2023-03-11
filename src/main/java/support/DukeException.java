package support;

/**
 * Custom exception for when user keys in correct command but wrong number of arguments.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
