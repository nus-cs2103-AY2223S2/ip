package Duke.command;

import Duke.Exceptions.DukeMainExceptions;
import Duke.Exceptions.NoDeadlineException;
import Duke.Exceptions.NoDescriptionException;
import Duke.Storage.Storage;
import Duke.Tasks.Event;
import Duke.TaskList;
import Duke.Ui;

public class AddEventCommand extends Command {
    private final String description;
    private final String startingTime;
    private final String endingTime;

    /**
     * Constructor that uses to create Event instance.
     * @param input The information of the task which includes event description,
     *              event starting time and event ending time.
     */
    public AddEventCommand(String input) {
        String[] splitDesWithFrom = input.split(" /from ", 2);
        this.description = splitDesWithFrom[0].trim();

        if (description.equals("")) {
            throw new NoDescriptionException("The description of an event cannot be empty.");
        } else if (splitDesWithFrom.length != 2) {
            throw new NoDeadlineException("The starting time cannot be empty");
        }

        String[] period = splitDesWithFrom[1].split(" /to ", 2);
        this.startingTime = period[0].trim();

        if (startingTime.equals("")) {
            throw new NoDeadlineException("The starting time cannot be empty.");
        } else if (period.length != 2) {
            throw new NoDeadlineException("The ending time cannot be empty.");
        }

        this.endingTime = period[1].trim();
    }

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
