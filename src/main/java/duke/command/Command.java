package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Models a command issued.
 */
public abstract class Command {
    public Command() {
    }

    public abstract String executeCommand(Storage storage, TaskList tasks) throws DukeException;
}
