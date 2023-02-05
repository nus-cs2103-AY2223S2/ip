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
     * @return
     * @throws DukeException
     */
    @Override
    public String executeCommand(TaskManager taskManager) throws DukeException {
        taskManager.displayList();
        return "";
    }
}
