package command;

import duke.DukeException;
import duke.TaskList;
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
        return Ui.getMarkMessageWithAttitude(this.taskList.markTask(this.index));
    }
}
