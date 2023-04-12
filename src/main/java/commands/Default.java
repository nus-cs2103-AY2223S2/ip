package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class Default implements Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showUnknownCommand();
    }

    public boolean isExit() {
        return false;
    }
}
