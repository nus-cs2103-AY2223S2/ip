package duke.command;

import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.task.Event;
import duke.task.TaskList;

import java.util.Date;

/**
 * Adds an Event type task.
 */
public class AddEventCommand extends Command {
    private String description;
    private String eventAt;
    private Date eventDate;

    /**
     * Main constructor
     *
     * @param description description of the task.
     * @param deadlineBy string of the deadline date.
     */
    public AddEventCommand(String description, String deadlineBy) {
        this.description = description;
        this.eventAt = deadlineBy;
    }

    /**
     * Main constructor
     *
     * @param description description of the task.
     * @param deadlineDate deadline date of the event.
     */
    public AddEventCommand(String description, Date deadlineDate) {
        this.description = description;
        this.eventDate = deadlineDate;
    }

    /**
     * Adds an Event type task.
     * Ask the UI to print the output.
     *
     * @param tasks Task list.
     * @param ui UI of the application to interact with users.
     * @param storage Storage to update when there is an update with the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Event event;
        if (eventDate == null) {
            event = new Event(description, eventAt);
        } else {
            event = new Event(description, eventDate);
        }
        tasks.add(event);
        Ui.showAddMessage(event, tasks.size());
        storage.save(tasks.getAllTasks());
    }
}