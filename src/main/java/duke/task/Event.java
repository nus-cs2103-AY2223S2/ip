package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event which has to from and to period.
 */
public class Event extends Task {
    private String start;
    private String end;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isStartDate;
    private boolean isEndDate;


    /**
     * Creates an instance of Event with the start and end dates.
     *
     * @param description Describes the event.
     * @param start Event starts at this time.
     * @param end Event ends at this time.
     */
    public Event(String description, String start, String end) {
        super(description);
        // Checks if the deadline provided is a valid date
        this.isStartDate = false;
        for (DateTimeFormatter format: Task.DATETIME_FORMATS) {
            try {
                this.startDate = LocalDateTime.parse(start, format);
                this.isStartDate = true;
                break;
            } catch (DateTimeParseException ex) {

            }
        }
        if (!isStartDate) {
            for (DateTimeFormatter format: Task.DATE_FORMATS) {
                try {
                    this.startDate = LocalDate.parse(start, format).atStartOfDay();
                    this.isStartDate = true;
                    break;
                } catch (DateTimeParseException ex) {

                }
            }
        }
        this.start = start;
        this.isEndDate = false;
        for (DateTimeFormatter format: Task.DATETIME_FORMATS) {
            try {
                this.endDate = LocalDateTime.parse(end, format);
                this.isEndDate = true;
                break;
            } catch (DateTimeParseException ex) {

            }
        }
        if (!isEndDate) {
            for (DateTimeFormatter format: Task.DATE_FORMATS) {
                try {
                    this.endDate = LocalDate.parse(end, format).atStartOfDay();
                    this.isEndDate = true;
                    break;
                } catch (DateTimeParseException ex) {

                }
            }
        }
        this.end = end;
    }

    /**
     * Returns a String representation of the Event.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        String startDateToPrint = this.isStartDate ? this.startDate.format(DEFAULT_DATETIME_FORMAT) : this.start;
        String endDateToPrint = this.isEndDate ? this.endDate.format(DEFAULT_DATETIME_FORMAT) : this.end;
        return String.format("[E]%s (from: %s to: %s)", super.toString(), startDateToPrint, endDateToPrint);
    }

    @Override
    public String toCsvString() {
        String startDateToPrint = this.isStartDate ? this.startDate.format(DEFAULT_DATETIME_FORMAT) : this.start;
        String endDateToPrint = this.isEndDate ? this.endDate.format(DEFAULT_DATETIME_FORMAT) : this.end;
        return String.format("E,%s,%s,%s", super.toCsvString(), startDateToPrint, endDateToPrint);
    }
}
