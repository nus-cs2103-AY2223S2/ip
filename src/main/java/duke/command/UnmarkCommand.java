package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Models a Unmark command issued.
 */
public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(String commandParams) {
        this.index = Integer.parseInt(commandParams);
    }

    @Override
    public String executeCommand(Storage storage, TaskList tasks) throws DukeException {
        String result = tasks.unMarkTaskWithResult(index);
        storage.save(tasks);
        return result;
    }
}
