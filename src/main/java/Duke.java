import interfaces.*;
import model.TaskModel;
import presenter.TaskPresenter;
import view.TaskView;

public class Duke {
    private boolean exit;
    private Duke() {
        this.exit = false;
    }
    private void exit() {
        this.exit = true;
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        TaskModel taskModel = new TaskModel();
        TaskView taskView = new TaskView();

        CommandEventListener exitEventListener = command -> {
            if (command.equalsIgnoreCase("bye")) {
                // cleanup code here
                duke.exit();
            }
        };

        Presenter presenter = new TaskPresenter(taskModel, taskView, exitEventListener);
        while(!duke.exit) {
            presenter.handleInput(taskView.getUserInput());
        }
    }
}
