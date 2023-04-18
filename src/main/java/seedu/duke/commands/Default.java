package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

public class Default implements Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showUnknownCommand();
    }

    public boolean isExit() {
        return false;
    }
}
