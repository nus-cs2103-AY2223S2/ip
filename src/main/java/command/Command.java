package command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui) throws DukeException;
    public abstract boolean isExit();
}
