package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(String commandParams) {
        this.index = Integer.parseInt(commandParams);
    }

    @Override
    public String executeCommand(Storage storage, TaskList tasks) throws DukeException {
        String result = tasks.deleteTaskWithResult(index);
        storage.save(tasks);
        return result;
    }
}
