package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that defines the Event type of tasks
 */
public class Event extends Task {

    protected String fromDate;
    /** Same value as fromDate, but stored as a LocalDate object */
    protected LocalDate startDate;
    protected String toDate;
    /** Same value as toDate, but stored as a LocalDate object */
    protected LocalDate endDate;

    /**
     * Constructor for objects of type Event
     *
     * @param userInput specifies the fromDate, toDate and title of an Event object
     */
    public Event(boolean isCompleted, String userInput) {
        super(isCompleted, userInput.substring(0, userInput.indexOf("/from ") - 1), TaskType.E);
        this.fromDate = userInput.substring(userInput.indexOf("/from ") + 6, userInput.indexOf("/to ") - 1);
        this.startDate = LocalDate.parse(fromDate);
        this.toDate = userInput.substring(userInput.indexOf("/to ") + 4);
        this.endDate = LocalDate.parse(toDate);
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String encode() {
        return super.encode() + " | " + this.fromDate + " | " + this.toDate;
    }
}
