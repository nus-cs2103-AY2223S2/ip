import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task{
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor to create a new instance of Task.
     * Tasks created are by default not completed.
     *
     * @param description Title of the task
     */
    public EventTask(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String fromString = from.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String toString = to.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        return "[E]" + super.toString() + " (from: " + fromString + " to: " + toString + ")";
    }
}
