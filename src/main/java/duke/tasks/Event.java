package duke.tasks;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * Encapsulates the Event class which is a sub-class of the Task class.
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Initialises the from and to field in LocalDate type.
     * 
     * @param description
     * @param by
     * @throws DateTimeException
     */
    public Event (String description, String from, String to) {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeException e) {
            throw new DateTimeException("Make sure the deadline in yyyy-mm-dd");
        }
    }

    @Override
    public String getTaskType() {
        return "E";
    }
    
    @Override 
    public String getTimeline() {
        return this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "," + this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}