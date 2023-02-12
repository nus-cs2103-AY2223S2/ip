package command;

import duke.DukeException;
import duke.TaskList;
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
        return Ui.getAddTaskConfirmationWithAttitude(this.taskList.addToDoTask(this.taskDetails));
    }
}
