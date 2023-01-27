package command;

import task.TaskManager;

import util.DukeException;
import util.Parser;
/**
 * Executes uncheck task from list command.
 */
public class UncheckCommand extends Command {
    private final TaskManager taskManager;
    private final int index;

    /**
     * Initialises task manager managing the list to add
     * tasks to and extracts list index from String input.
     *
     * @param taskManager
     * @param input
     */
    public UncheckCommand(TaskManager taskManager, String input) {
        this.taskManager = taskManager;
        this.index = super.extractIndex(input) - 1;
    }

    /**
     * Unchecks task at index given by user.
     *
     * @throws DukeException
     */
    @Override
    public void executeCommand() throws DukeException {
        try {
            taskManager.uncheckTask(this.index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Item does not exist in list! Please check your list again.");
        }
    }
}
