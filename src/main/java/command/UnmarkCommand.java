package command;

import task.TaskManager;
import util.DukeException;
import util.DukeUI;

/**
 * Executes uncheck task from list command.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Extracts and initialises list index from String input.
     * <p>
     * @param input
     */
    public UnmarkCommand(String input) {
        this.index = super.extractIndex(input) - 1;
    }

    /**
     * Unmarks task at index given by user as completed.
     * <p>
     * @param taskManager
     * @return Success message that task is uncompleted.
     * @throws DukeException
     */
    @Override
    public String executeCommand(TaskManager taskManager) throws DukeException {
        try {
            assert taskManager != null;
            taskManager.unmarkTask(this.index);
            return DukeUI.unmarkTaskMessage() + taskManager.printTask(this.index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(DukeUI.indexErrorMessage());
        }
    }
}
