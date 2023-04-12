package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class Exit implements Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    public boolean isExit() {
        return true;
    }
}
