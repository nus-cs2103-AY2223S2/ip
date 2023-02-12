package command;

import duke.DukeException;
import task.Task;
import task.TaskList;
import duke.Ui;

/**
 * Command to add todo task.
 */
public class CommandToDo extends Command {

    private final TaskList taskList;
    private final String taskDetails;

    /**
     * Constructor for CommandToDo.
     *
     * @param taskList List of all tasks.
     * @param taskDetails Details of todo type task to be added.
     */
    public CommandToDo(TaskList taskList, String taskDetails) {
        this.taskList = taskList;
        this.taskDetails = taskDetails;
    }
    @Override
    public String execute() throws DukeException {
        Task taskAdded = this.addIntoList(this.taskDetails);
        return this.getConfirmationMessageOf(taskAdded);
    }

    private Task addIntoList(String taskDetails) throws DukeException {
        return this.taskList.addToDoTask(taskDetails);
    }

    private String getConfirmationMessageOf(Task taskAdded) {
        return Ui.getAddTaskConfirmationWithAttitudeOf(taskAdded);
    }
}
