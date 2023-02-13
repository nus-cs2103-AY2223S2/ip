package command;

import duke.DukeException;
import task.Task;
import task.TaskList;
import duke.Ui;

/**
 * Command to mark a task as done.
 */
public class CommandMark extends Command {

    private final TaskList taskList;
    private final String index;

    /**
     * Constructor for CommandMark.
     *
     * @param taskList List of all tasks.
     * @param index Index of task to mark, starting from 1.
     */
    public CommandMark(TaskList taskList, String index) {
        this.taskList = taskList;
        this.index = index;
    }

    @Override
    public String execute() throws DukeException {
        Task markedTask = this.markTaskAt(this.index);
        return this.getConfirmationMessageOf(markedTask);
    }

    private Task markTaskAt(String index) throws DukeException {
        int i = this.getIndex(index);
        return this.taskList.getTaskAt(i).markIsDone();
    }

    private int getIndex(String index) throws DukeException {
        try {
            return Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private String getConfirmationMessageOf(Task markedTask) {
        return Ui.getMarkMessageWithAttitude(markedTask);
    }
}
