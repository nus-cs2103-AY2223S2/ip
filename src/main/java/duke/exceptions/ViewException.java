package duke.exceptions;
/**
 *
 * The {@code ViewException} class represents an exception that is thrown
 * when an error occurs in the view.
 */
public class ViewException extends DukeException {
    public ViewException(String errorMessage) {
        super("View error" + errorMessage);
    }
}
