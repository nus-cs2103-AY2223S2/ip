package duke.command;

import duke.task.Event;

/**
 * Adds an event to the task list.
 */
public class AddEventCommand extends Command {
    private final Event event;

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
    public String execute() {
        assert event != null : "Event cannot be null";
        assert taskList != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        taskList.addTask(event);
        return ui.printTaskAdded(event, taskList.getSize());
    }
}
