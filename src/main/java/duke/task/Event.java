package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.parser.DateTimeParser;
import duke.ui.Ui;

/**
 * Represents an Event, which is a type of Task that starts at a specific datetime and ends at a specific datetime.
 */
public class Event extends Task {
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

    /**
     * Constructor for Event class that sets the description and event start and end date times.
     *
     * @param description Description of Event.
     * @throws DukeException Throws exception if unable to set date times.
     */
    public Event(String description) throws DukeException {
        super(description.split(" /from ")[0]);
        setEventDateTimes(description);
    }

    /**
     * Constructor for Event class that sets the description, event start and end date times, and event status.
     *
     * @param description Description of Event.
     * @throws DukeException Throws exception if unable to set date times.
     */
    public Event(String description, String taskStatus) throws DukeException {
        super(description.split(" /from ")[0]);
        setEventDateTimes(description);
        markTaskIfNeeded(taskStatus, this);
    }

    @Override
    public LocalDateTime getDate() {
        return startDateTime;
    }

    /**
     * Returns the String representation of an Event.
     *
     * @return String representation of an Event in this format:
     *     [E][{status}] {description} (from: {start datetime} to: {end datetime}).
     */
    @Override
    public String toString() {
        return String.format("[E][%c] %s\n(from: %s to: %s) %s", getStatusIcon(), description,
                Ui.getStringDateTime(startDateTime), Ui.getStringDateTime(endDateTime),
                super.getUrgentMessage(startDateTime));
    }

    private void setEventDateTimes(String description) throws DukeException {
        try {
            String dateTimes = description.split(" /from ")[1];
            startDateTime = DateTimeParser.parse(dateTimes.split(" /to ")[0]);
            endDateTime = DateTimeParser.parse(dateTimes.split(" /to ")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("I'm sorry, but Fake Duke doesn't know what that means :-(");
        } catch (DateTimeParseException dtpe) {
            throw new DukeException("Invalid datetime format. Please use yyyy-mm-dd HH:mm (E.g. 2019-10-15 18:00).");
        }
    }

    /**
     * Returns the raw String representation of an Event to be stored in the local file for storage.
     *
     * @return Raw String representation of a Task in this format:
     *     E ~ {status} ~ {description} ~ {start datetime} ~ {end datetime}.
     */
    @Override
    public String getRawTask() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("E ~ %d ~ %s ~ %s ~ %s\n", isDone ? 1 : 0, description,
                dtf.format(startDateTime), dtf.format(endDateTime));
    }
}
