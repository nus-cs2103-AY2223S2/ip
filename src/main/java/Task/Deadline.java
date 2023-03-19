package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * Represents a Task with a deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;
    protected DateTimeFormatter format;

    /**
     * Initialises new instance of Deadline.
     *
     * @param description The name of the task.
     * @param by The deadline for the task in a String.
     * @param format The datetime format for the given time in String.
     */
    public Deadline(String description, String by, DateTimeFormatter format) {
        super(description);
        this.by = LocalDateTime.parse(by, format);
        this.format = format;
    }

    /**
     * Initialises new instance of Deadline.
     *
     * @param description The name of the task.
     * @param isDone A boolean representing whether task has been completed.
     * @param by The deadline for the task in a String.
     * @param format The datetime format for the given time in String.
     */
    public Deadline(String description, boolean isDone, String by, DateTimeFormatter format) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, format);
        this.format = format;
    }

    /**
     * Edits the selected task.
     *
     * @param item The item to be updated.
     * @param newInformation The new information.
     * @throws DukeException
     */
    @Override
    public void edit(String item, String newInformation) throws DukeException {
        switch(item) {
        case "name":
            this.name = newInformation;
            break;

        case "by":
            this.by = LocalDateTime.parse(newInformation, format);
            break;

        default:
            throw new DukeException(duke.Ui.invalidItemUpdateResponse(this));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("D | %s | by: %s", super.toString(), by.format(format));
    }

}

