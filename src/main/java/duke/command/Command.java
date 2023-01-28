package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public abstract class Command {
    public boolean isBye() {
        return false;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;
}