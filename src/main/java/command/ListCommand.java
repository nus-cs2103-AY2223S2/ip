package command;

import task.TaskManager;
import util.DukeException;

/**
 * Executes display task list command.
 */
public class ListCommand extends Command {
    private final TaskManager taskManager;

    public ListCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Displays the task list when prompted by user.
     *
     * @throws DukeException
     */
    @Override
    public void executeCommand() throws DukeException {
        taskManager.displayList();
    }
}
