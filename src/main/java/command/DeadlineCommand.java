package command;

import task.Deadline;
import task.TaskManager;

import util.DukeException;

/**
 * Executes add deadline task command.
 */
public class DeadlineCommand extends Command {
    //private final TaskManager taskManager;
    private final String description;
    public DeadlineCommand(String description) {
        //this.taskManager = taskManager;
        this.description = description;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds a deadline type task to the list.
     * <p>
     * String input is parsed to extract date and
     * time the task is due by.
     *
     * @return
     * @throws DukeException
     */
    @Override
    public String executeCommand(TaskManager taskManager) throws DukeException {
        try {
            String[] tmp = this.description.split(" /by ");
            Deadline deadline = new Deadline(tmp[0], false, tmp[1]);
            assert taskManager != null;
            taskManager.addTaskToList(deadline);
            String str = String.format("I have added: %s !", deadline);
            String str2 = "There are currently " + taskManager.getTaskArraySize() + " task(s) in the list!";
            return str + System.lineSeparator() + str2;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please add a description, date and time e.g. homework /by 12/12/12 2359");
        }
    }
}
