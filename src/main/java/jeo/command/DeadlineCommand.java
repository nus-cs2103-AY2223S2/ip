package jeo.command;

import jeo.database.TaskList;
import jeo.task.Deadline;
import jeo.ui.Ui;

/**
 * Represents a Deadline Command to create a deadline task.
 * @author Goh Jun How
 * @version 0.3
 */
public class DeadlineCommand extends Command {
    private final Deadline task;

    /**
     * Creates a DeadlineCommand object.
     * @param task Deadline task
     */
    public DeadlineCommand(Deadline task) {
        this.task = task;
    }

    /**
     * Executes the Deadline Command.
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
        return "deadline";
    }
}
