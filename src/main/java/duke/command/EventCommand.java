package duke.command;

import java.io.IOException;
import java.time.LocalDateTime;

import duke.task.Event;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command that adds an event to the task list.
 */
public class EventCommand extends Command {

    private final Event task;

    /**
     * Represents the constructor for an EventCommand object.
     *
     * @param description Description of the event.
     * @param fromTime Start time of the event.
     * @param toTime End time of the event.
     */
    public EventCommand(String description, LocalDateTime fromTime, LocalDateTime toTime) {
        this.task = new Event(description, fromTime, toTime);
    }

    /**
     * Returns the command as a String which is used to show to the user in the GUI.
     * Executes the command and adds an event to the task list.
     *
     * @param tl TaskList which the Duke will modify.
     * @param ui Ui to be used to facilitate interactions between user and the CLI.
     * @param storage Storage to be used to handle interactions with the save file.
     * @throws DukeException If there is an error in executing the command.
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        tl.add(task);
        String toShow = "Meowww, I've added this task:\nAdded: " + task + "\n";
        toShow += ui.stringOfTaskNumbers(tl);
        ui.showToUser(toShow);

        try {
            storage.update(tl);
        } catch (IOException e) {
            ui.showToUser(e.getMessage());
            throw new DukeException("Problem with updating in Event");
        }
        return toShow;
    }
}
