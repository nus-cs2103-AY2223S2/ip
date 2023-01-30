package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command{

    protected int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void executeCommand(TaskList taskList, Storage storage, Ui ui) {
        ui.printText(" Noted. I've removed this task:");
        taskList.deleteTask(index);
        ui.printText("Now you have " + taskList.numberOfTask() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
