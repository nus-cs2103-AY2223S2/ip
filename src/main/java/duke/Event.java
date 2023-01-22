/**
 * Represents an Event task. An Event task has a description,
 * a 'from' and 'to' timeframe, and can be marked as done.
 */

package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Event extends Task {
    protected String from;
    protected String to;
    protected LocalDate fromDate;
    protected Optional<LocalTime> fromTime;
    protected LocalDate toDate;
    protected Optional<LocalTime> toTime;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        String[] splitFrom = from.split(" ");
        this.fromDate = LocalDate.parse(splitFrom[0]);
        if (splitFrom.length > 1) {
            // Time is specified by user
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
            this.fromTime = Optional.of(LocalTime.parse(splitFrom[1], formatter));
        } else {
            this.fromTime = Optional.empty();
        }
        String[] splitTo = to.split(" ");
        this.toDate = LocalDate.parse(splitTo[0]);
        if (splitTo.length > 1) {
            // Time is specified by user
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
            this.toTime = Optional.of(LocalTime.parse(splitTo[1], formatter));
        } else {
            this.toTime = Optional.empty();
        }
    }

    public Event(String description, boolean bool, String from, String to) {
        super(description, bool);
        this.from = from;
        this.to = to;
        String[] splitFrom = from.split(" ");
        this.fromDate = LocalDate.parse(splitFrom[0]);
        if (splitFrom.length > 1) {
            // Time is specified by user
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
            this.fromTime = Optional.of(LocalTime.parse(splitFrom[1], formatter));
        } else {
            this.fromTime = Optional.empty();
        }
        String[] splitTo = to.split(" ");
        this.toDate = LocalDate.parse(splitTo[0]);
        if (splitTo.length > 1) {
            // Time is specified by user
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
            this.toTime = Optional.of(LocalTime.parse(splitTo[1], formatter));
        } else {
            this.toTime = Optional.empty();
        }
    }

    /**
     * Marks an Event task as done.
     * @return Event task marked as done.
     */
    @Override
    public Event markAsDone() {
        return new Event(description, true, from, to);
    }

    /**
     * Unmarks an Event task from being done.
     * @return Event task unmarked from being done.
     */
    @Override
    public Event unmarkAsDone() {
        return new Event(description, from, to);
    }

    /**
     * Returns data for storage purposes.
     * @return Data for storage purposes.
     */
    @Override
    public String getDataToSave() { return "E / " + getStatusNum() + " / " + getDesc() + " / " + from + " / " + to; }

    @Override
    public String toString() {
        if (fromTime.isEmpty() && toTime.isEmpty()) {
            return "[E]" + super.toString() + " (from: "
                    + fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: "
                    + toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        } else if (fromTime.isEmpty()) {
            return "[E]" + super.toString() + " (from: "
                    + fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: "
                    + toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                    + toTime.get().format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
        } else if (toTime.isEmpty()) {
            return "[E]" + super.toString() + " (from: "
                    + fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                    + fromTime.get().format(DateTimeFormatter.ofPattern("HH:mm")) + " to: "
                    + toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        } else {
            return "[E]" + super.toString() + " (from: "
                    + fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                    + fromTime.get().format(DateTimeFormatter.ofPattern("HH:mm")) + " to: "
                    + toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                    + toTime.get().format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
        }
    }
}