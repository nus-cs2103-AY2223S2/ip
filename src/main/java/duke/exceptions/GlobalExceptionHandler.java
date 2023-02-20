package duke.exceptions;

import duke.interfaces.View;

/**
 * A general exception handler to handle DukeExceptions caught in Duke's event loop.
 */
public class GlobalExceptionHandler {
    private final View taskView;
    public GlobalExceptionHandler(View taskView) {
        this.taskView = taskView;
    }

    public void handleException(DukeException exception) {
        // determine if exception is recoverable or not
        if (exception instanceof PresenterException | exception instanceof CommandException) {
            taskView.showError(exception.getMessage());
        }
    }
}
