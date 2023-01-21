package duke.command;

import duke.DukeException;
import duke.task.Event;

public class AddEventCommand extends Command {
    private Event event;

    public AddEventCommand(Event event) {
        this.event = event;
    }

    @Override
    public void execute() throws DukeException {
        taskList.addTask(event);
        ui.printTaskAdded(event, taskList.getSize());
    }
}