package duke.command;

import duke.TaskList;
import duke.ui.Ui;
import duke.Task;
import duke.storage.Storage;

public class MarkCommand extends Command {
    private int taskID;
    
    public MarkCommand(int taskID) {
        this.taskID = taskID;
    }

    @Override
    public void initCommand(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() >= taskID && !tasks.isEmpty()) {
            Task currentTask = tasks.getTask(taskID);
            currentTask.mark();
            storage.saveData(tasks);
            ui.displayMarkMessage(currentTask);

        } else {
            ui.displayMsg("Invalid taskID entered!");
        }
    }
    
}
