package command;

import task.TaskManager;
import util.DukeException;
import util.DukeUI;

/**
 * Executes mark task as completed in list command.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Extracts and initialises list index from String input.
     * <p>
     * @param input
     */
    public MarkCommand(String input) {
        this.index = super.extractIndex(input) - 1;
    }

    /**
     * Marks task at index given by user as completed.
     * <p>
     * @param taskManager
     * @return Success message that task has been completed
     * @throws DukeException
     */
    @Override
    public String executeCommand(TaskManager taskManager) throws DukeException {
        try {
            assert taskManager != null;
            taskManager.markTask(this.index);
            return DukeUI.markTaskMessage() + taskManager.printTask(this.index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(DukeUI.indexErrorMessage());
        }
    }
}
