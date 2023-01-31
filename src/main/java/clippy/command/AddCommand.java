package clippy.command;

import clippy.task.TaskList;
import clippy.ui.Ui;

public abstract class AddCommand extends Command {

    protected void printCreatedTaskStatus(TaskList taskList, Ui ui) {
        ui.prettyPrint("Got it! I've added this task:");
        ui.prettyPrint(taskList.getLastTask().toString());
        ui.prettyPrint(String.format("Now you have %d task%s in the list.",
                taskList.getSize(), taskList.getSize() == 1 ? "" : "s"));
    }
}
