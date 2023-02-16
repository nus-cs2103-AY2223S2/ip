package seedu.duke.commands;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();
}
