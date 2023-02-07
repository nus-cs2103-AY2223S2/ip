package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Skeleton class for all Commands.
 */
public class Command {
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return "You did not specify your command.";
    }
}
