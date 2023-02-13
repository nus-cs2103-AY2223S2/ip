package duke.Command;

import duke.Exception.InvalidArgumentsException;

import duke.Task.Event;

import duke.Utilities.NoteList;
import duke.Utilities.TaskList;
import duke.Utilities.UI;
import duke.Utilities.Storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Event command which is executed by duke.Utilities.Duke.
 */
public class EventCommand extends Command {

    private final String name;
    private final LocalDateTime from;
    private final LocalDateTime until;

    /**
     * Constructor for an (executable) Event command.
     * @param name The name of the event.
     * @param from The start date/time of the event.
     * @param until The end date/time of the event.
     * @throws InvalidArgumentsException Exception thrown when the date/time input is given in an invalid format.
     */
    public EventCommand(String name, String from, String until) throws InvalidArgumentsException {
        this.name = name;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.from = LocalDateTime.parse(from.trim(), formatter);
            this.until = LocalDateTime.parse(until.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentsException();
        }
    }

    /**
     * Creates a new Event task which is added to the task list.
     * The confirmation is then displayed on the UI, and the changes to task list are saved to file.
     * @param tasks The task list to add to/edit/delete from.
     * @param ui The UI to display the confirmation messages/error messages to users.
     * @param storage The storage which to store to when a task is added/deleted or its status is changed.
     */
    @Override
    public String execute(TaskList tasks, NoteList notes, UI ui, Storage storage) {
        Event ev = new Event(name, from, until);
        String confirmationMessage = tasks.addTask(ev);
        storage.saveToFile(tasks.getTasks(), notes.getNotes());
        return confirmationMessage;
    }
    @Override
    public boolean isByeCommand() {
        return false;
    }
}
