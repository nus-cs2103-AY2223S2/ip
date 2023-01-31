package duke.task;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event, which is a type of Task that starts at a specific datetime and ends at a specific datetime.
 */
public class Event extends Task {
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

    public Event(String description) throws DukeException {
        super(description.split(" /from ")[0]);
        setEventDateTimes(description);
    }

    public Event(String description, String taskStatus) throws DukeException {
        super(description.split(" /from ")[0]);
        setEventDateTimes(description);

        if (taskStatus.equals("1")) {
            this.mark();
        }
    }

    /**
     * Returns the String representation of an Event.
     *
     * @return String representation of an Event in this format:
     * [E][{status}] {description} (from: {start datetime} to: {end datetime}).
     */
    @Override
    public String toString() {
        Ui ui = new Ui();
        return String.format("[E][%c] %s (from: %s to: %s) %s", this.getStatusIcon(), this.description,
                ui.getStringDateTime(this.startDateTime), ui.getStringDateTime(this.endDateTime),
                super.urgentMessage(this.startDateTime));
    }

    private void setEventDateTimes(String description) throws DukeException {
        Parser parser = new Parser();
        try {
            String dateTimes = description.split(" /from ")[1];
            this.startDateTime = parser.parseDateTime(dateTimes.split(" /to ")[0]);
            this.endDateTime = parser.parseDateTime(dateTimes.split(" /to ")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ I'm sorry, but Fake Duke doesn't know what that means :-(");
        } catch (DateTimeParseException dtpe) {
            throw new DukeException("Invalid datetime format. Please use yyyy-mm-dd HH:mm (E.g. 2019-10-15 18:00).");
        }
    }

    /**
     * Returns the raw String representation of an Event to be stored in the local file for storage.
     *
     * @return Raw String representation of a Task in this format:
     * E ~ {status} ~ {description} ~ {start datetime} ~ {end datetime}.
     */
    @Override
    public String getRawTask() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("E ~ %d ~ %s ~ %s ~ %s\n", isDone ? 1 : 0, this.description,
                dtf.format(this.startDateTime), dtf.format(this.endDateTime));
    }
}
