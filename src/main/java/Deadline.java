import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public LocalDateTime date;

    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.type = 'D';
        this.date = date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + type + "]" + "[" + getStatusIcon()+ "] " + this.description + " (by: " + 
                date.format(DateTimeFormatter.ofPattern("DD MMM yyyy hh:mma")) + ")";
    }

}
