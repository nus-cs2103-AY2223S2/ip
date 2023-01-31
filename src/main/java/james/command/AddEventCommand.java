package james.command;

import james.JamesException;
import james.task.Event;

/**
 * The command to add an event to the task list.
 */
public class AddEventCommand extends Command {
    private Event event;

    public AddEventCommand(Event event) {
        this.event = event;
    }

    @Override
    public void execute() throws JamesException {
        taskList.addToTaskList(event);
        ui.addTask(event, taskList.getSize());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof addEventCommand) {
            return event.equals(((addEventCommand) obj).event);
        }
        return false;
    }
}