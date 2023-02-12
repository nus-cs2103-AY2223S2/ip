package command;

import duke.DukeException;
import task.Task;
import task.TaskList;
import duke.Ui;

/**
 * Command to add event task.
 */
public class CommandEvent extends Command {

    private final TaskList taskList;
    private final String taskDetails;

    /**
     * Constructor for CommandEvent.
     *
     * @param taskList List of all tasks.
     * @param taskDetails Details of event type task to be added.
     */
    public CommandEvent(TaskList taskList, String taskDetails) {
        this.taskList = taskList;
        this.taskDetails = taskDetails;
    }

    @Override
    public String execute() throws DukeException {
        Task taskAdded = this.addIntoList(this.taskDetails);
        return this.getConfirmationMessageOf(taskAdded);
    }

    private Task addIntoList(String taskDetails) throws DukeException {
        return this.taskList.addEventTask(taskDetails);
    }

    private String getConfirmationMessageOf(Task taskAdded) {
        return Ui.getAddTaskConfirmationWithAttitudeOf(taskAdded);
    }


}
