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

    /**
     * Handle DukeExceptions by printing their message to the view.
     * @param exception The exception to be handled.
     */
    public void handleException(DukeException exception) {
        // determine if exception is recoverable or not
        taskView.showError(exception.getMessage());
    }
}
