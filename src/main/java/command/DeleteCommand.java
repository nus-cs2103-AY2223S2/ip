package command;
import task.TaskManager;
import util.DukeException;
import util.Parser;

/**
 * Executes delete task command.
 */
public class DeleteCommand extends Command {
    private final TaskManager taskManager;
    private final int index;

    /**
     * Initialises task manager managing the list
     * and extracts list index from String input.
     *
     * @param taskManager
     * @param input
     */
    public DeleteCommand(TaskManager taskManager, String input) {
        this.taskManager = taskManager;
        this.index = super.extractIndex(input) - 1;
    }

    /**
     * Deletes task at index given by user.
     *
     * String input is parsed to extract start and end
     * dates and timings of the event.
     *
     * @throws DukeException
     */
    @Override
    public void executeCommand() throws DukeException {
        try {
            taskManager.deleteTask(this.index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Item does not exist in list! Please check your list again.");
        }
    }
}
