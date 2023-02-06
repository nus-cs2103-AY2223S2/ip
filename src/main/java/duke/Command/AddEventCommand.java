package duke.Command;

import duke.Exceptions.DukeException;
import duke.Storage;
import duke.Tasks.Event;
import duke.Tasks.TaskList;
import duke.Ui;

public class AddEventCommand extends Command {
    private final String description;
    private final String by;
    private final String to;

    public AddEventCommand(String description, String by, String to) {
        this.description = description;
        this.by = by;
        this.to = to;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        Event newEvent = new Event(this.description, this.by, this.to);
        tasks.addTask(newEvent);
        ui.showTaskAdded(newEvent, tasks);
        return newEvent.toString();
    }
}
