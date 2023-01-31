package clippy.command;

import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.Ui;

public class MarkCommand extends Command {
    int taskIdToMark;

    public MarkCommand(int taskIdToMark) {
        this.taskIdToMark = taskIdToMark;
    }
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        ui.prettyPrint("Great job! I've marked this task as done: ");
        ui.prettyPrint(taskList.mark(taskIdToMark).toString());
    }
}
