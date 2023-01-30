package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class MarkCommand extends Command {
    protected int index;
    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void executeCommand(TaskList taskList, Storage storage, Ui ui) {

        taskList.markTask(index);
        Task task = taskList.getTasks().get(index-1);

        ui.printText("Nice! I've marked this task as done:");
        ui.printText(" " + task.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
