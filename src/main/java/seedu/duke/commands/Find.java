package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

public class Find implements Command {
    private String query;

    public Find(String query) {
        this.query = query;
    }

    public void execute (TaskList tasks, Ui ui, Storage storage) {
        ui.showFindResults();
        ui.showTasks(tasks.search(query));
    }

    public boolean isExit() {
        return false;
    }
}
