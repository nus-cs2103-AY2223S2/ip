package command;

import task.TaskManager;

import util.DukeException;

/**
 * Executes uncheck task from list command.
 */
public class UncheckCommand extends Command {
    //private final TaskManager taskManager;
    private final int index;

    /**
     * Initialises task manager managing the list to add
     * tasks to and extracts list index from String input.
     *
     * @param taskManager
     * @param input
     */
    public UncheckCommand(String input) {
        //this.taskManager = taskManager;
        this.index = super.extractIndex(input) - 1;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Unchecks task at index given by user.
     *
     * @return
     * @throws DukeException
     */
    @Override
    public String executeCommand(TaskManager taskManager) throws DukeException {
        try {
            assert taskManager != null;
            taskManager.uncheckTask(this.index);
            String str = "No worries! I have unchecked this task in the list: ";
            return str + System.lineSeparator() + taskManager.printTask(this.index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Item does not exist in list! Please check your list again.");
        }
    }
}
