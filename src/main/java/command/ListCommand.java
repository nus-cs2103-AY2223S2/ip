package command;

import task.TaskManager;
import util.DukeException;

/**
 * Executes display task list command.
 */
public class ListCommand extends Command {

    public ListCommand() {}

    /**
     * Displays the task list when prompted by user.
     * <p>
     * @param taskManager
     * @return String of tasks in list
     * @throws DukeException
     */
    @Override
    public String executeCommand(TaskManager taskManager) throws DukeException {
        assert taskManager != null;
        return taskManager.displayList();
    }
}
