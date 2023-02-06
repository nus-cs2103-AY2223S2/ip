package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public abstract class Command {
    protected boolean exit = false;

    public boolean isExit() {
        return exit;
    }

    public abstract String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException;
}
