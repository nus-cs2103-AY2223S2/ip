package tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String start;
    protected String end;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected LocalTime startTime;
    protected LocalTime endTime;

    /**
     * Constructor for Event.
     *
     * @param description The description of the event.
     * @param start The start date and time of the event.
     * @param end The end date and time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        handleDateAndTime();
    }

    /**
     * Returns the start date and time of the event.
     *
     * @return The start date and time of the event.
     */
    public void handleDateAndTime() {
        if (start.contains(" ")) {
            LocalDate newStartDate = LocalDate.parse(start.split(" ")[0]);
            LocalTime newStartTime = LocalTime.parse(start.split(" ")[1]);
            this.startDate = newStartDate;
            this.startTime = newStartTime;
        } else {
            LocalDate newStartDate = LocalDate.parse(start);
            this.startDate = newStartDate;
        }

        if (end.contains(" ")) {
            LocalDate newEndDate = LocalDate.parse(end.split(" ")[0]);
            LocalTime newEndTime = LocalTime.parse(end.split(" ")[1]);
            this.endDate = newEndDate;
            this.endTime = newEndTime;
        } else {
            LocalDate newEndDate = LocalDate.parse(end);
            this.endDate = newEndDate;
        }
    }

    /**
     * Returns the start date and time of the event.
     *
     * @return The start date and time of the event.
     */
    public String printStartDateAndTime() {
        if (!start.contains(" ")) {
            return startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ", " + startTime.getHour() + startTime.getMinute();
        }
    }

    /**
     * Returns the end date and time of the event.
     *
     * @return The end date and time of the event.
     */
    public String printEndDateAndTime() {
        if (!end.contains(" ")) {
            return endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return endDate.format(DateTimeFormatter.ofPattern("MMM dd  yyyy")) + ", " + endTime.getHour() + endTime.getMinute();
        }
    }

    /**
     * Returns the string representation of the event.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.getDescription() + " (from: " + printStartDateAndTime() + " to: " + printEndDateAndTime() + ")";
    }

    /**
     * Returns the string representation of the event to be written to the file.
     *
     * @return The string representation of the event to be written to the file.
     */
    @Override
    public String writeToFile() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.getDescription() + " (from: " + start + " to: " + end + ")";
    }
}
