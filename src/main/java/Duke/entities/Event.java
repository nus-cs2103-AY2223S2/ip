package Duke.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Event Task of the Chat bot.
 */
public class Event extends Task {
    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Instantiates an Event Object that can be placed into the TaskList.
     *
     * @param description The description of the task.
     * @param startdate   The start date of the event.
     * @param enddate     The end date of the event.
     */
    public Event(String description, String startdate, String enddate) {
        super(description);
        this.startDate = LocalDate.parse(startdate.trim());
        this.endDate = LocalDate.parse(enddate.trim());
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * Process the current Event object to be saved into a text file.
     *
     * @return a String representing the Event to be saved.
     */
    @Override
    public String toSave() {
        if (super.isDone == true) {
            return String.format("E | 1 | %s | %s | %s\n", super.getDescription(), this.startDate, this.endDate);
        }
        return String.format("E | 0 | %s | %s | %s\n", super.getDescription(), this.startDate, this.endDate);
    }

    /**
     * Process the current Event object to be displayed.
     *
     * @return a String representing the Event to be displayed by the User Interface.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
