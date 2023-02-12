package command;

import duke.DukeException;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Command to add deadline task.
 */
public class CommandDeadline extends Command {

    private final TaskList taskList;
    private final String taskDetails;

    /**
     * Constructor for CommandDeadline.
     *
     * @param taskList List of all tasks
     * @param taskDetails Details of deadline type task to be added.
     */
    public CommandDeadline(TaskList taskList, String taskDetails) {
        this.taskList = taskList;
        this.taskDetails = taskDetails;
    }

    @Override
    public String execute() throws DukeException {
        Task taskAdded = this.addIntoList(this.taskDetails);
        return this.getConfirmationMessageOf(taskAdded);
    }

    private Task addIntoList(String taskDetails) throws DukeException {
        return this.taskList.addDeadlineTask(taskDetails);
    }

    private String getConfirmationMessageOf(Task taskAdded) {
        return Ui.getAddTaskConfirmationWithAttitudeOf(taskAdded);
    }
}
