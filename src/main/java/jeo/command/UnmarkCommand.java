package jeo.command;

import jeo.database.TaskList;
import jeo.task.Task;
import jeo.ui.Ui;

/**
 * Represents an Unmark Command to mark a task as completed.
 * @author Goh Jun How
 * @version 0.3
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Creates an unmarkCommand object.
     * @param index index denoting task number of task to be unmarked
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark Command.
     * @param ui UI for displaying the output message
     * @param taskList taskList to be processed
     * @return the output message
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        Task task = taskList.getTaskAtIndex(index);
        taskList.unmarkTask(index);
        return ui.taskUnmarkedMessage(task);
    }

    /**
     * Represents the command type.
     * @return String representation of the command type
     */
    @Override
    public String toString() {
        return "unmark";
    }
}
