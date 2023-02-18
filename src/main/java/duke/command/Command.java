package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command class, with a function execute() to execute the command
 */
public abstract class Command {
    public abstract String execute(TaskList tl, Ui ui, Storage storage) throws DukeException;

}
