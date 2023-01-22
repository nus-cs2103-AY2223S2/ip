package command;

import duke.TaskList;
import duke.Ui;
import task.Task;

/**
 * Command to add a task.
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.add(this.task);
        ui.showSuccess("Got it. I've added this task:");
        ui.showSuccess(this.task.toString());
        ui.showSuccess("Now there are " + tasks.getNoOfTasks() + " tasks in your list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
