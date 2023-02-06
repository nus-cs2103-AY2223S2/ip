package duke.command;

import duke.TaskList;
import duke.ui.Ui;
import duke.Task;
import duke.storage.Storage;

public class DeleteCommand extends Command {
    private int taskID;

    public DeleteCommand(int taskID) {
        this.taskID = taskID;
    }

    @Override
    public void initCommand(TaskList tasks, Ui ui, Storage storage) {
        // TODO Auto-generated method stub
        if (tasks.size() >= taskID && !tasks.isEmpty()) {
            Task currentTask = tasks.getTask(taskID);
            tasks.deleteTask(taskID);
            storage.saveData(tasks);
            ui.displayDeleteTaskMessage(currentTask, tasks);

        } else {
            ui.displayMsg("Invalid taskID entered!");
        }
    }

    
}
