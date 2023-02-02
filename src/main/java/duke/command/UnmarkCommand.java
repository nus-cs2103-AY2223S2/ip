package duke.command;

import duke.Task;
import duke.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class UnmarkCommand extends Command {
    private int taskID;

    public UnmarkCommand(int taskID) {
        this.taskID = taskID;
    }

    @Override
    public void initCommand(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() >= taskID && !tasks.isEmpty()) {
            Task currentTask = tasks.geTask(taskID);
            currentTask.unmark();
            storage.saveData(tasks);
            ui.displayUnmarkMessage(currentTask);

        } else {
            ui.displayMsg("Invalid taskID entered!");
        }
    }
    
}
