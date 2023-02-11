package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that defines the Event type of tasks
 */
public class Event extends Task {

    /** Start date for this event stored as a String*/
    protected String fromDate;
    /** Start date for this event stored as a LocalDate*/
    protected LocalDate startDate;
    /** End date for this event */
    protected String toDate;
    /** End date for this event stored as a LocalDate*/
    protected LocalDate endDate;

    /**
     * Constructor for objects of type Event
     *
     * @param userInput specifies the fromDate, toDate and title of an Event object
     */
    public Event(boolean isCompleted, String userInput) {
        super(isCompleted, userInput.substring(6, userInput.indexOf("/from ") - 1), 'E');
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
