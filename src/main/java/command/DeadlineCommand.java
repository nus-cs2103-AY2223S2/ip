package command;

import task.Deadline;
import task.TaskManager;
import util.DukeException;
import util.DukeUI;

/**
 * Executes add deadline task command.
 */
public class DeadlineCommand extends Command {
    private final String description;

    /**
     * Executes command to add a deadline task to the list.
     * <p>
     * @param description
     */
    public DeadlineCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a deadline type task to the list.
     * <p>
     * String input is parsed to extract date and
     * time the task is due by.
     * <p>
     * @param taskManager
     * @return Successful add deadline message
     * @throws DukeException
     */
    @Override
    public String executeCommand(TaskManager taskManager) throws DukeException {
        try {
            String[] tmp = this.description.split(" /by ");
            Deadline deadline = new Deadline(tmp[0], false, tmp[1]);
            assert taskManager != null;
            taskManager.addTaskToList(deadline);
            return DukeUI.deadlineTaskMessage(deadline, taskManager.getTaskArraySize());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(DukeUI.deadlineFormatErrorMessage());
        }
    }
}
