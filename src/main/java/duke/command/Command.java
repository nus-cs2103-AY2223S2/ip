package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public abstract class Command {
    public abstract void execute(TaskList tl, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
