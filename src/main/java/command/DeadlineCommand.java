package command;

import task.Deadline;
import task.TaskManager;

import util.DukeException;

public class DeadlineCommand extends Command {
    private final TaskManager taskManager;
    private final String description;
    public DeadlineCommand(TaskManager taskManager, String description) {
        this.taskManager = taskManager;
        this.description = description;
    }
    @Override
    public void executeCommand() throws DukeException {
        try {
            String[] tmp = this.description.split(" /by ");
            Deadline deadline = new Deadline(tmp[0], false, tmp[1]);
            taskManager.addTaskToList(deadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please add a description, date and time e.g. homework /by 12/12/12 2359");
        }
    }
}
