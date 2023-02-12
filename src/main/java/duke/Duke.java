package duke;

import duke.exceptions.DukeException;
import duke.exceptions.GlobalExceptionHandler;
import duke.interfaces.CommandEventListener;
import duke.interfaces.Presenter;
import duke.model.TaskModel;
import duke.presenter.TaskPresenter;
import duke.view.TaskView;

/**
 * The main class containing Duke's event loop.
 */
public class Duke {
    private boolean isTerminated;
    private Duke() {
        this.isTerminated = false;
    }
    private void exit() {
        this.isTerminated = true;
    }
    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke();
        TaskModel taskModel = new TaskModel();
        TaskView taskView = new TaskView();
        GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler(taskView);
        CommandEventListener exitEventListener = command -> {
            if (command.equalsIgnoreCase("bye")) {
                // cleanup code here
                duke.exit();
            }
        };

        Presenter presenter = new TaskPresenter(taskModel, taskView, exitEventListener);
        while (!duke.isTerminated) {
            try {
                presenter.handleInput(taskView.getUserInput());
            } catch (DukeException e) {
                exceptionHandler.handleException(e);
            }
        }
    }
}
