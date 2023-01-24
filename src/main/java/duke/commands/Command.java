package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
