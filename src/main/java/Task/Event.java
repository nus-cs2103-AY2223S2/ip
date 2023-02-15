package duke.task;

import duke.DukeException;
import duke.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task with a duration.
 */
public class Event extends Task {

    protected LocalDateTime to;
    protected LocalDateTime from;
    protected DateTimeFormatter format;

    /**
     * Initialises new instance of Event.
     *
     * @param description The name of the Task.
     * @param from The starting date of the task in a String.
     * @param to The ending date of the task in a String.
     * @param format The datetime format for the given time in String.
     */
    public Event(String description, String from, String to, DateTimeFormatter format) {
        super(description);
        this.from = LocalDateTime.parse(from, format);
        this.to = LocalDateTime.parse(to, format);
        this.format = format;
    }

    /**
     * Initialises new instance of Event.
     *
     * @param description The name of the Task.
     * @param isDone A boolean representing whether task has been completed.
     * @param from The starting date of the task in a String.
     * @param to The ending date of the task in a String.
     * @param format The datetime format for the given time in String.
     */
    public Event(String description, boolean isDone, String from, String to, DateTimeFormatter format) {
        super(description, isDone);
        this.from = LocalDateTime.parse(from, format);
        this.to = LocalDateTime.parse(to, format);
        this.format = format;
    }

    /**
     * Edits the selected task.
     *
     * @param item The item to be updated.
     * @param newInformation The new information.
     * @throws duke.DukeException
     */
    @Override
    public void edit(String item, String newInformation) throws DukeException {
        switch(item) {
        case "name":
            this.name = newInformation;
            break;

        case "to":
            this.to = LocalDateTime.parse(newInformation, format);
            break;

        case "from":
            this.from = LocalDateTime.parse(newInformation, format);
            break;

        default:
            throw new DukeException(Ui.invalidItemUpdateResponse(this));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("E | %s | from: %s | to: %s", super.toString(), from.format(format), to.format(format));
    }

}
