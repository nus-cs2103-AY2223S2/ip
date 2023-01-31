package clippy.command;

import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        if (taskList.isEmpty()) {
            ui.prettyPrint("No tasks found!");
        }
        for (int i = 1; i <= taskList.getSize(); i++) {
            ui.prettyPrint(String.format("%d. %s", i, taskList.get(i)));
        }
    }
}
