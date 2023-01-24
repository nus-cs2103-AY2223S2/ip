package duke.commands;

import duke.exceptions.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

abstract public class Command {

    abstract public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    abstract public boolean isByeCommand();
}
