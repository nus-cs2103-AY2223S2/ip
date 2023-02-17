package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.parser.DateTimeParser;
import duke.ui.Ui;

/**
 * Represents an Event, which is a type of Task that starts and ends at specific date times.
 */
public class Event extends Task {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    /**
     * Creates an Event object.
     * Sets the description and event start and end date times.
     *
     * @param input for event.
     * @throws DukeException if unable to set date times.
     */
    public Event(String input) throws DukeException {
        super(input.split(" /from ")[0]);
        setEventDateTimes(input);
    }

    /**
     * Constructor for Event class that sets the description, event start and end date times, and event status.
     *
     * @param input for event.
     * @throws DukeException if unable to set date times.
     */
    public Event(String input, String taskStatus) throws DukeException {
        super(input.split(" /from ")[0]);
        setEventDateTimes(input);
        markTaskIfNeeded(taskStatus, this);
    }

    private void setEventDateTimes(String input) throws DukeException {
        try {
            String dateTimes = input.split(" /from ")[1];
            startDateTime = DateTimeParser.parse(dateTimes.split(" /to ")[0]);
            endDateTime = DateTimeParser.parse(dateTimes.split(" /to ")[1]);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            throw new DukeException("I'm sorry, but Fake Duke doesn't know what that means :-(");
        } catch (DateTimeParseException dtpe) {
            throw new DukeException("Invalid datetime format. Please use yyyy-mm-dd HH:mm (E.g. 2019-10-15 18:00).");
        }
    }

    @Override
    public LocalDateTime getDate() {
        return startDateTime;
    }

    @Override
    public String getRawTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("E ~ %d ~ %s ~ %s ~ %s\n", isDone ? 1 : 0, description,
                formatter.format(startDateTime), formatter.format(endDateTime));
    }

    /**
     * Returns the String representation of an Event.
     *
     * @return String representation of an Event in this format:
     *     [E][{status}] {description} (from: {start datetime} to: {end datetime}).
     */
    @Override
    public String toString() {
        return String.format("[E][%c] %s\n(from: %s\nto: %s)%s", getStatusIcon(), description,
                Ui.getStringDateTime(startDateTime), Ui.getStringDateTime(endDateTime),
                super.getUrgentMessage(startDateTime));
    }
}
