package task;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Event class inherits from the Task class and is used for representing all
 * event tasks, which is defined with an event start date and time, as well
 * as an event end date and time.
 *
 * @author      Tseng Chen-Yu
 * @version     %I%, %G%
 * @since       1.0
 */
public class Event extends Task {
    private LocalDate eventStartDate;
    private LocalDate eventEndDate;
    private Date eventStartTime;
    private Date eventEndTime;

    /**
     * Constructor for Event class
     *
     * @param description Description for the event task.
     * @param eventStartDate Start date for the event task.
     * @param eventStartTime Start time for the event task.
     * @param eventEndDate End date for the event task.
     * @param eventEndDate End time for the event task.
     * @param isDone Event task completion status.
     */
    public Event(String description, LocalDate eventStartDate, Date eventStartTime,
                 LocalDate eventEndDate, Date eventEndTime, boolean isDone) {
        super(description, isDone);
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    /**
     * Retrieves the event start date and time.
     *
     * @return Formatted event start date and time, separated by a single space.
     *
     */
    public String getEventStart() {
        String formattedStartTime = new SimpleDateFormat("hh:mm").format(eventStartTime);
        return eventStartDate + " " + formattedStartTime;
    }

    /**
     * Retrieves the event end date and time.
     *
     * @return Formatted end start date and time, separated by a single space.
     *
     */
    public String getEventEnd() {
        String formattedEndTime = new SimpleDateFormat("hh:mm").format(eventEndTime);
        return eventEndDate + " " + formattedEndTime;
    }

    /**
     * Overrides the default toString method.
     *
     * @return A text UI representing an event entry, displaying the task type, completion status, description
     *         and the event start date and time, as well as the event end date and time.
     *
     */
    @Override
    public String toString() {
        String formattedStartDate = eventStartDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedEndDate = eventEndDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedStartTime = new SimpleDateFormat("h:mm a").format(eventStartTime);
        String formattedEndTime = new SimpleDateFormat("h:mm a").format(eventEndTime);
        String eventStartCombined = formattedStartDate + " " + formattedStartTime;
        String eventEndCombined = formattedEndDate + " " + formattedEndTime;
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (from: " + eventStartCombined
                + " to: " + eventEndCombined + ")";
    }
}
