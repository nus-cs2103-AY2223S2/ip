package james.command;

import james.JamesException;
import james.task.Event;

/**
 * The command to add an event to the task list.
 */
public class addEventCommand extends Command {
    private Event event;

    public addEventCommand(Event event) {
        this.event = event;
    }

    @Override
    public void execute() throws JamesException {
        taskList.addToTaskList(event);
        ui.addTask(event, taskList.getSize());
    }
}