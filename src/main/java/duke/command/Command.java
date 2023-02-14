package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * A Command interface for various types of commands to inherit from.
 */
public interface Command {
    String execute(Storage storage, TaskList tasks, Ui ui) throws DukeException;
}
