package duke.Command;

import duke.Storage;
import duke.TaskList;

public class MarkCommand extends Command {

    private int index;

    public MarkCommand(String commandParams) {
        int index = Integer.parseInt(commandParams);
    }

    @Override
    public String executeCommand(Storage storage, TaskList tasks) {
        String result = tasks.markTaskWithResult(index);
        storage.save(tasks);
        return result;
    }
}
