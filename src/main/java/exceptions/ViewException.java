package exceptions;

public class ViewException extends DukeException {
    public ViewException(String errorMessage) {
        super("View error" + errorMessage);
    }
}
