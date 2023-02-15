package duke.Command;

import duke.Storage;
import duke.TaskList;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(String commandParams) {
        int index = Integer.parseInt(commandParams);
    }

    @Override
    public String executeCommand(Storage storage, TaskList tasks) {
        String result = tasks.deleteTaskWithResult(index);
        storage.save(tasks);
        return result;
    }
}
