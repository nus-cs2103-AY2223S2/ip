package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class AddCommand extends Command {

    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void executeCommand(TaskList taskList, Storage storage, Ui ui) {
        ui.printText("Got it. I've added this task:");
        taskList.addTask(task);
        ui.printText(" " + task);

        ui.printText("Now you have " + taskList.getNumberOfTask() + " tasks in the list.");

    }

    @Override
    public boolean isExit() {
        return false;
    }

}
