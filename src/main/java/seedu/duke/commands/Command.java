package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

public interface Command {
    public void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit();
}
