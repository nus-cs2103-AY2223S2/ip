package clippy.command;

import clippy.command.Command;
import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.Ui;

public class UnmarkCommand extends Command {
    int taskIdToUnmark;

    public UnmarkCommand(int taskIdToUnmark) {
        this.taskIdToUnmark = taskIdToUnmark;
    }
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        ui.prettyPrint("Aww... I've marked this task as not done yet: ");
        ui.prettyPrint(taskList.unmark(taskIdToUnmark).toString());
    }
}
