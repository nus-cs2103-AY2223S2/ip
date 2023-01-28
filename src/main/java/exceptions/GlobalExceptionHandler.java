package exceptions;

import view.TaskView;

public class GlobalExceptionHandler {
    private final TaskView taskView;
    public GlobalExceptionHandler(view.TaskView taskView){
        this.taskView = taskView;
    }

    public void handleException(DukeException exception) {
        // determine if exception is recoverable or not
        if (exception instanceof PresenterException | exception instanceof CommandException) {
            taskView.showError(exception.getMessage());
        }
    }
}
