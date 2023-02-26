package duke.command;

import duke.Ui;
import duke.Storage;
import duke.task.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveList(tasks);
        ui.closeUi();
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
