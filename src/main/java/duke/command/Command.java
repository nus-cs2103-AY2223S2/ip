package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
