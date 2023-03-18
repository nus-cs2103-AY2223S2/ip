package jeo.command;

import jeo.database.TaskList;
import jeo.task.Event;
import jeo.ui.Ui;

/**
 * Represents an Event Command to create an Event task.
 * @author Goh Jun How
 * @version 0.3
 */
public class EventCommand extends Command {
    private final Event task;

    /**
     * Creates an EventCommand object.
     * @param task Event task
     */
    public EventCommand(Event task) {
        this.task = task;
    }

    /**
     * Executes the Event Command.
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
        return "event";
    }
}
