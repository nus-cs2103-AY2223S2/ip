package command;

import duke.DukeException;
import duke.Ui;
import task.Deadline;
import task.Task;
import task.TaskList;

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
        try {
            String[] s = taskDetails.split("/by");
            String taskInfo = s[0].trim();
            String taskDateLine = s[1].trim();

            Task newTask = new Deadline(taskInfo, taskDateLine);
            this.taskList.addTask(newTask);
            return newTask;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(Ui.emptyDetailsForDeadlineMessage);
        }
    }

    private String getConfirmationMessageOf(Task taskAdded) {
        return Ui.getAddTaskConfirmationWithAttitudeOf(taskAdded);
    }
}
