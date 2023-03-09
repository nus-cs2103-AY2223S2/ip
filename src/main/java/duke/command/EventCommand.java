package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * EventCommand is a command that creates a new Event.
 */
public class EventCommand extends Command {
    private String description;
    private String from;
    private String by;

    /**
     * A Constructor for EventCommand.
     *
     * @param description Description of Event.
     * @param from Start date of Event.
     * @param by End date of Event.
     */
    public EventCommand(String description, String from, String by) {
        super(false);
        this.description = description;
        this.from = from;
        this.by = by;
    }

    /**
     * Creates a new Event task and adds it into the tasklist.
     *
     * @param tasks Tasklist containing the list of tasks.
     * @param storage Saves tasks into the file locally.
     * @param ui Deals with interactions with user.
     * @return String response from Duke.
     * @throws DukeException if duke does not recognise the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        tasks.addEvent(description, from, by);
        int size = tasks.getSize();
        Task temp = tasks.getTask(size - 1);
        storage.saveTasksToFile(tasks.getListOfTasks());
        return ui.showAdd(temp, size);
    }
}
