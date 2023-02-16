package duke;
/**
 * > Child class of Exception that is made for custom duke exceptions
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
