package duke.Command;

import duke.Storage;
import duke.TaskList;

public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(String commandParams) {
        int index = Integer.parseInt(commandParams);
    }

    @Override
    public String executeCommand(Storage storage, TaskList tasks) {
        String result = tasks.unMarkTaskWithResult(index);
        storage.save(tasks);
        return result;
    }
}
