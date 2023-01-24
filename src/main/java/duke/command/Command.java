package duke.command;

import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public abstract class Command {
    public abstract boolean isGoodbye();
    public abstract void execute(TaskList tasks, Storage storage, Ui ui);
}
