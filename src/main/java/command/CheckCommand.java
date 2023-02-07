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
     * @return
     * @throws DukeException
     */
    @Override
    public String executeCommand(TaskManager taskManager) throws DukeException {
        try {
            assert taskManager != null;
            taskManager.checkTask(this.index);
            String str = "Great job! I have checked this task off the list:";
            return str + System.lineSeparator() + taskManager.printTask(this.index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Item does not exist in list! Please check your list again.");
        }
    }
}
