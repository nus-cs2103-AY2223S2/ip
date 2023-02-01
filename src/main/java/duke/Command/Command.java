package duke.Command;

import duke.Exceptions.DukeException;
import duke.Storage;
import duke.Tasks.TaskList;
import duke.Ui;

public abstract class Command {
    protected boolean exit;

    public boolean isExit() {
        return exit;
    }

    public abstract String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException;
}
