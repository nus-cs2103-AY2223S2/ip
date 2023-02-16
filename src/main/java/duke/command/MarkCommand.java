package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Models a Mark command issued.
 */
public class MarkCommand extends Command {

    private int index;

    public MarkCommand(String commandParams) {
        this.index = Integer.parseInt(commandParams);
    }

    @Override
    public String executeCommand(Storage storage, TaskList tasks) throws DukeException {
        String result = tasks.markTaskWithResult(index);
        storage.save(tasks);
        return result;
    }
}
