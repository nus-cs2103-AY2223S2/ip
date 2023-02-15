package command;

import task.TaskManager;
import util.DukeException;
import util.DukeUI;

/**
 * Executes delete task command.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Extracts and initialises list index from String input.
     * <p>
     * @param input
     */
    public DeleteCommand(String input) {
        this.index = super.extractIndex(input) - 1;
    }

    /**
     * Executes deletion of task at index given by user.
     * <p>
     * @param taskManager
     * @return Successful task deletion message.
     * @throws DukeException
     */
    @Override
    public String executeCommand(TaskManager taskManager) throws DukeException {
        try {
            assert taskManager != null;
            taskManager.deleteTask(this.index);
            return DukeUI.deleteTaskMessage(taskManager.getTaskArraySize());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(DukeUI.indexErrorMessage());
        }
    }
}
