package duke.tasks;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.Ui;

/**
 * The Event object that extends Task.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructor for an Event object.
     *
     * @param description The Event description.
     * @param from The start date of the Event.
     * @param to The end date of the Event.
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        LocalDateTime fromDateTime;
        LocalDateTime toDateTime;
        try {
            fromDateTime = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

            this.from = fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        } catch (DateTimeParseException e) {
            LocalDate fromDate = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.from = fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            fromDateTime = fromDate.atStartOfDay();
        }
        if (!fromDateTime.isAfter(LocalDateTime.now())) {
            throw new DukeException(Ui.datePassed());
        }
        try {
            toDateTime = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.to = toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        } catch (DateTimeParseException e) {
            LocalDate toDate = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.to = toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            toDateTime = toDate.atTime(23, 59);
        }
        if (!toDateTime.isAfter(fromDateTime)) {
            throw new DukeException(Ui.startDatelaterThanEnd());
        }
    }

    /**
     * Check if the event's dates contains the given keyword.
     *
     * @param keyword The keyword argument.
     * @return A boolean value.
     */
    public boolean dateContains(String keyword) {
        keyword = keyword.toLowerCase();
        if ((this.from.length() >= keyword.length()) || (this.to.length() >= keyword.length())) {
            return this.from.toLowerCase().contains(keyword) || this.to.toLowerCase().contains(keyword);
        } else {
            return false;
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "\n (from: " + from + " to: " + to + ")\n";
    }
}
