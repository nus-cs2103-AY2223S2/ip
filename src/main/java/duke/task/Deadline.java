package duke.task;

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
     * Return date and time
     * 
     * @return String representation of date and time in this format "DD MMM yyyy hh:mma"
     */
    public String getDateTime() {
        return date.format(DateTimeFormatter.ofPattern("DD MMM yyyy hh:mma"));
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
