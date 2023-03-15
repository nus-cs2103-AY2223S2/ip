package jeo.command;

import jeo.database.TaskList;
import jeo.task.ToDo;
import jeo.ui.Ui;

/**
 * Represents a Todo Command to create an Event task.
 * @author Goh Jun How
 * @version 0.3
 */
public class TodoCommand extends Command {
    private final ToDo task;

    /**
     * Creates a TodoCommand object.
     * @param task Todo task
     */
    public TodoCommand(ToDo task) {
        this.task = task;
    }

    /**
     * Executes the Todo Command.
     * @param ui UI for displaying the output message
     * @param taskList taskList to be processed
     * @return the output message
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        taskList.addTask(task);
        return ui.taskAddedMessage(task, taskList.getNumberOfTasks());
    }

    /**
     * Represents the command type.
     * @return String representation of the command type
     */
    @Override
    public String toString() {
        return "todo";
    }
}
