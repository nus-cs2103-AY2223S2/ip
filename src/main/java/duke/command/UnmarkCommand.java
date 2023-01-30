package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class UnmarkCommand extends Command{
    protected int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void executeCommand(TaskList taskList, Storage storage, Ui ui) {
        taskList.unmarkTask(index);
        Task task = taskList.getTasks().get(index - 1);

        ui.printText("OK, I've marked this task as not done yet:");
        ui.printText(" " + task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
