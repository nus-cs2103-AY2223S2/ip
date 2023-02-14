package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;
import duke.enums.Views;

/**
 * Event object that has a to and from date object
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Takes in title and from and to to create an event
     *
     * Will try and parse it as date and throw exception when it cannot
     *
     * @param title of the Task that that is being created
     * @param from  of the event starting date
     * @param to    of the event ending date
     * @throws DukeException
     */
    public Event(String title, String from, String to) throws DukeException {
        this(title, from, to, false);
    }

    /**
     * This method sets the isDone status directly. Mostly used from Storage
     *
     * @param title  of the Task that that is being created
     * @param from   of the event starting date
     * @param to     of the event ending date
     * @param isDone status of the Task
     * @throws DukeException
     */
    public Event(String title, String from, String to, boolean isDone) throws DukeException {
        super(title, isDone);
        try {
            this.from = LocalDateTime.parse(from.replace("/from", "").trim());
            this.to = LocalDateTime.parse(to.replace("/to", "").trim());
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException(Views.DATE_PARSE_ERR_STRING);
        }
        if (this.to.isBefore(this.from)) {
            throw new DukeException(Views.DATE_WRONG_ORDER_STRING);
        }
        assert this.to.isAfter(this.from) : Views.DATE_WRONG_ORDER_STRING.str();
    }

    /**
     * Method for formatting the Task to store in a txt file
     *
     * @return String of the Task
     */
    @Override
    public String toExport() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Get a String representation to display to user of a Task
     *
     * @return String representation of the Task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}
