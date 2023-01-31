package james.command;

import james.JamesException;
import james.task.Event;

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
}