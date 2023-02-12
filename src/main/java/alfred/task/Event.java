package alfred.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Child class of task that represents a task with a start and end date.
 */
public class Event extends Task {

    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Creates an instance of event.
     *
     * @param description
     * @param startDate
     * @param endDate
     */
    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getStorageDetails() {
        return this.description + " | " + this.startDate.toString() + " - " + this.endDate.toString();
    }

    @Override
    public String getEventType() {
        return "E";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return "[E]" + super.toString() + " (from: " + this.startDate.format(formatter) + " to: "
                + this.endDate.format(formatter) + ")";
    }
}
