package command;

import task.TaskManager;

import util.DukeException;

/**
 * Executes display task list command.
 */
public class ListCommand extends Command {
    //private final TaskManager taskManager;

    public ListCommand() {

        //this.taskManager = taskManager;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Displays the task list when prompted by user.
     *
     * @throws DukeException
     */
    @Override
    public void executeCommand(TaskManager taskManager) throws DukeException {
        taskManager.displayList();
    }
}
