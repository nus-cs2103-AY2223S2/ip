package clippy.command;

import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        ui.systemPrint("Saving state, please wait...");
        storage.saveState(taskList.getList());
        ui.systemPrint("State successfully saved.");
        ui.prettyPrint("Hope I helped. Goodbye!");
    }

    @Override
    public boolean shouldContinue() {
        return false;
    }
}
