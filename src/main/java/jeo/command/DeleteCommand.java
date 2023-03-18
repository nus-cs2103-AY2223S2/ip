package jeo.command;

import jeo.database.TaskList;
import jeo.task.Task;
import jeo.ui.Ui;

/**
 * Represents a Delete Command to delete a task.
 * @author Goh Jun How
 * @version 0.3
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Creates a DeleteCommand object.
     * @param index index denoting task number of task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the Delete Command.
     * @param ui UI for displaying the output message
     * @param taskList taskList to be processed
     * @return the output message
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        Task task = taskList.getTaskAtIndex(index);
        taskList.deleteTask(index);
        return ui.taskDeletedMessage(task, taskList.getNumberOfTasks());
    }

    /**
     * Represents the command type.
     * @return String representation of the command type
     */
    @Override
    public String toString() {
        return "delete";
    }
}
