package Duke.command;

import Duke.Exceptions.DukeMainExceptions;
import Duke.Storage.Storage;
import Duke.Tasks.Event;
import Duke.TaskList;
import Duke.Ui;
public class AddEventCommand extends Command {
    private final String description;
    private final String startingTime;
    private final String endingTime;

    public AddEventCommand(String description, String startingTime, String endingTime) {
        this.description = description;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeMainExceptions {
        Event newEvent = new Event(this.description, this.startingTime, this.endingTime);
        tasks.addTask(newEvent, storage);
        return ui.printAddedTask(newEvent, tasks);
    }
}
