package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Contains information of an event
 * Contains description and duration of the event
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Creates an event object
     *
     * @param description The description of the event
     * @param from Starting time of the event
     * @param to Ending time of the event
     * @throws DukeException If specified from and to timings could not be parsed to datetime
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        try {
            this.from = LocalDate.parse(from);
        } catch (DateTimeParseException e) {
            throw new DukeException("Starting time could not be parsed to datetime");
        }
        try {
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new DukeException("Ending time could not be parsed to datetime");
        }
    }

    /**
     * Creates a Event object
     *
     * @param description The description of the deadline
     * @param from duke.task.Deadline time of the deadline
     * @param to duke.task.Deadline time of the deadline
     * @param isDone Completion status of task
     * @throws DukeException If specified from and to timings could not be parsed to datetime
     */
    public Event(String description, String from, String to, boolean isDone) throws DukeException {
        super(description, isDone);
        try {
            this.from = LocalDate.parse(from);
        } catch (DateTimeParseException e) {
            throw new DukeException("Starting time could not be parsed to datetime");
        }
        try {
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new DukeException("Ending time could not be parsed to datetime");
        }
    }

    /**
     * Generate a Event object from user's command input
     *
     * @param input The user's command input
     * @throws DukeException If the input from the user is missing some fields
     */
    public static Event generate(String input) throws DukeException {
        // Cleans input and checks for description and duration
        try {
            input = input.trim()
                    .substring(6)
                    .trim();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Event missing description and event duration");
        }

        // Generates duke.task.Event task
        try {
            String[] data = input.split(" /from | /to ");
            return new Event(data[0], data[1], data[2]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Event missing fields");
        }
    }

    /**
     * Generate a Event object from saved data
     *
     * @param input The saved data of the task
     * @param isDone The completion status of the task
     * @throws DukeException If saved data of the event task is missing some fields
     */
    public static Event load(String input, boolean isDone) throws DukeException {
        try {
            String[] data = input.split(" \\| ");
            String[] time = data[1].split(" - ");
            return new Event(data[0], time[0], time[1], isDone);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Event missing fields");
        }
    }

    /**
     * Returns type of task, completion status, description, and duration of
     * the event
     *
     * @return Type of task, completion status, description, and duration of
     * the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from
                        .format(DateTimeFormatter
                                .ofPattern("MMM d yyyy"))
                + " to: " + to
                        .format(DateTimeFormatter
                                .ofPattern("MMM d yyyy"))
                + ")";
    }

    /**
     * @inherit
     * Returns the Event task's saved data in string format
     */
    @Override
    public String save() {
        return "E | " + getStatusIcon()
                + " | " + description
                + " | " + from
                + " - " + to;
    }
}
