package duke.commands;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public abstract class Command {

    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

}
