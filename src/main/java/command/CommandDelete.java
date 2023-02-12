package command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * Command to delete a task.
 */
public class CommandDelete extends Command {

    private final TaskList taskList;
    private final String index;

    /**
     * Constructor for CommandDelete.
     *
     * @param taskList List of all tasks.
     * @param index Index of task, starting from 1.
     */
    public CommandDelete(TaskList taskList, String index) {
        this.taskList = taskList;
        this.index = index;
    }

    @Override
    public String execute() throws DukeException {
        return Ui.getDeleteMessageWithAttitude(this.taskList.deleteTask(this.index));
    }
}
