package duke.task;

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
     * Return date and time of from and to
     * 
     * @return String representation of date and time from and to in this format "DD MMM yyyy hh:mma"
     */
    public String getDateTime() {
        return dateTimeFrom.format(DateTimeFormatter.ofPattern("DD MMM yyyy hh:mma")) + " - " +
                dateTimeTo.format(DateTimeFormatter.ofPattern("DD MMM yyyy hh:mma"));
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