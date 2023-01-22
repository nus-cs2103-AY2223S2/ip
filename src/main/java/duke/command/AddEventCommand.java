package duke.command;

import duke.task.Event;

public class AddEventCommand extends Command {
    private Event event;

    public AddEventCommand(Event event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddEventCommand) {
            return event.equals(((AddEventCommand) obj).event);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("AddEventCommand{event=%s}", event);
    }

    @Override
    public void execute() {
        taskList.addTask(event);
        ui.printTaskAdded(event, taskList.getSize());
    }
}
