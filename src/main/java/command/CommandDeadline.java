package command;

import duke.DukeException;
import duke.Ui;
import storage.Storage;
import task.Deadline;
import task.Task;
import task.TaskList;

/**
 * Command to add deadline task.
 */
public class CommandDeadline extends Command {

    private final TaskList taskList;
    private final String taskDetails;
    private final Storage storage;

    /**
     * Constructor for CommandDeadline.
     *
     * @param taskList List of all tasks
     * @param taskDetails Details of deadline type task to be added.
     */
    public CommandDeadline(TaskList taskList, String taskDetails, Storage storage) {
        this.taskList = taskList;
        this.taskDetails = taskDetails;
        this.storage = storage;
    }

    @Override
    public String execute() throws DukeException {
        Task taskAdded = this.addIntoList(this.taskDetails);
        this.updateFile();
        return this.getConfirmationMessageOf(taskAdded);
    }

    private void updateFile() throws DukeException {
        this.storage.overwriteFile(this.taskList);
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
            throw new DukeException(Ui.getEmptyDetailsForDeadlineMessage());
        }
    }

    private String getConfirmationMessageOf(Task taskAdded) {
        return Ui.getAddTaskConfirmationWithAttitudeOf(taskAdded);
    }
}
