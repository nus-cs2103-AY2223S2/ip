package duke.command;

import duke.Ui;
import duke.TaskList;
import duke.Storage;
import duke.DukeException;


public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }

}
