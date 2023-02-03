package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** Class that represents an event */
public class Event extends Task {

    private LocalDate starDate;
    private LocalDate endDate;

    /**
     * Constructs an Event object with
     * the given name, start date,
     * and end date.
     *
     * @param name Name of this event.
     * @param startDate Date that this event starts.
     * @param endDate Date that this event ends.
     * @throws DateTimeParseException if startDate or endDate are not validly formatted dates.
     */
    public Event(String name, String startDate, String endDate) throws DateTimeParseException {
        super(name);
        this.starDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: "
            + this.starDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: "
            + this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getFileRepresentation() {
        return "E" + "@" + (super.isDone() ? "1" : "0")
                + "@" + this.getName() + "@" + this.starDate + "@" + this.endDate;
    }

}
