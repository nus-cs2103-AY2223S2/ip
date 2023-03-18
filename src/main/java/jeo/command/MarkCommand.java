package jeo.command;

import jeo.database.TaskList;
import jeo.task.Task;
import jeo.ui.Ui;

/**
 * Represents a Mark Command to mark a task as completed.
 * @author Goh Jun How
 * @version 0.3
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Creates a MarkCommand object.
     * @param index index denoting task number of task to be marked
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the Mark Command.
     * @param ui UI for displaying the output message
     * @param taskList taskList to be processed
     * @return the output message
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        Task task = taskList.getTaskAtIndex(index);
        taskList.markTask(index);
        return ui.taskMarkedMessage(task);
    }

    /**
     * Represents the command type.
     * @return String representation of the command type
     */
    @Override
    public String toString() {
        return "mark";
    }
}
