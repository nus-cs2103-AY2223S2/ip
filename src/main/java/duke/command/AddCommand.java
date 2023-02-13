package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.task);
        assert taskList.getTasks().contains(this.task) : "Task can be found inside tasklist";
        return ("Got it. I've added this task: \n Now you have " + taskList.getNumTasks() + " tasks in your list");
    }

    public boolean isExit() {
        return false;
    }
}
