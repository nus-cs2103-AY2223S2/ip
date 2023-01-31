package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    public boolean isBye() {
        return false;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;
}
