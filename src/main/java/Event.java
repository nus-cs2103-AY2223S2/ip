import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public LocalDateTime dateTimeFrom;
    public LocalDateTime dateTimeTo;
    public Event(String description, LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo) {
        super(description);
        this.type = 'E';
        this.dateTimeFrom = dateTimeFrom;
        this.dateTimeTo = dateTimeTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + type + "]" + "[" + getStatusIcon()+ "] " + this.description + " (from: " + 
                dateTimeFrom.format(DateTimeFormatter.ofPattern("DD MMM yyyy hh:mma")) + " to: " +
                dateTimeTo.format(DateTimeFormatter.ofPattern("DD MMM yyyy hh:mma")) + ")";
    }

}