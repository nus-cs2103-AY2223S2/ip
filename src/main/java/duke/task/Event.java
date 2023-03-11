package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has both a starting date
 * and an ending date.
 */
public class Event extends Task{

    private LocalDate startDate;
    private LocalDate endDate;

    public Event(String name, LocalDate startDate, LocalDate endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.startDate.format(DateTimeFormatter.ofPattern("d MMM uuuu"))
                + " to: " + this.endDate.format(DateTimeFormatter.ofPattern("d MMM uuuu")) + ")";
    }
}
