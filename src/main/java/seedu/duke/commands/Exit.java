package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

public class Exit implements Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    public boolean isExit() {
        return true;
    }
}
