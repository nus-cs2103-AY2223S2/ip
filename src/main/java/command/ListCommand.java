package command;

import task.TaskManager;
import util.DukeException;

public class ListCommand extends Command {
    private final TaskManager taskManager;

    public ListCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void executeCommand() throws DukeException {
        taskManager.displayList();
    }
}
