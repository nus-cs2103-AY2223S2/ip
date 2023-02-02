package command;

import task.TaskManager;

import util.DukeException;

/**
 * Executes check task in list command.
 */
public class CheckCommand extends Command {
    // private final TaskManager taskManager;
    private final int index;

    /**
     * Initialises task manager managing the list
     * and extracts list index from String input.
     *
     * @param taskManager
     * @param input
     */
    public CheckCommand(String input) {
        //this.taskManager = taskManager;
        this.index = super.extractIndex(input) - 1;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Checks task at index given by user.
     *
     * @throws DukeException
     */
    @Override
    public void executeCommand(TaskManager taskManager) throws DukeException {
        try {
            taskManager.checkTask(this.index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Item does not exist in list! Please check your list again.");
        }
    }
}
