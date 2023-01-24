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
        Presenter presenter = new TaskPresenter(taskModel, taskView);
        CommandEventListener ExitCommandListener = command -> {
            if (command.equalsIgnoreCase("bye")) {
                // cleanup code here
                duke.exit();
            }
        };

        presenter.registerListener(ExitCommandListener);

        while(!duke.exit) {
            presenter.handleInput(taskView.getUserInput());
        }
    }
}
