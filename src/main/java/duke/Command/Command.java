package duke.Command;

import duke.Exception.DukeException;

import duke.Storage;
import duke.TaskList;
import duke.UI;

public abstract class Command {

    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;

}
