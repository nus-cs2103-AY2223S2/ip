import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event, which is a type of Task that starts at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {
    protected LocalDateTime startDatetime;
    protected LocalDateTime endDatetime;

    public Event(String description) throws DukeException {
        super(description.split(" /from ")[0]);
        try {
            String datetimes = description.split(" /from ")[1];
            this.startDatetime = parseDatetime(datetimes.split(" /to ")[0]);
            this.endDatetime = parseDatetime(datetimes.split(" /to ")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ I'm sorry, but Fake Duke doesn't know what that means :-(");
        } catch (DateTimeParseException dtpe) {
            throw new DukeException("Invalid datetime format. Please use yyyy-mm-dd HH:mm (E.g. 2019-10-15 18:00).");
        }
    }

    /**
     * Returns the String representation of an Event.
     *
     * @return  String representation of an Event in this format: [E][<status>] <description> (from: <start date/time>
     * to: <end date/time>).
     */
    @Override
    public String toString() {
        return String.format("[E][%c] %s (from:%s to:%s)", this.getStatusIcon(), this.description
                , this.getStringDatetime(this.startDatetime), this.getStringDatetime(this.endDatetime));
    }
}
