package duke.command;

import duke.TaskList;
import duke.ui.Ui;
import duke.Task;
import duke.storage.Storage;


public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void initCommand(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.task);
        storage.saveData(tasks);
        ui.displayAddTaskMessage(task, tasks);
    }
    
}
