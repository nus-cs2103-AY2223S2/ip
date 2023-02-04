package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;
    protected String taskType;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
        this.taskType = "E";
    }

    /**
     * Returns String of the Event task type, status, description from-date and to-date.
     * <p>
     * TaskType will be "E" for Event.
     * <p>
     * Status will be either "X" or " " depending on mark or unmarked respectively.
     * <p>
     * @return String in the format: [taskType][status] description /from DD-MMM-YYYY /to DD-MMM-YYYY
     */
    public String toString() {
        String formattedFromDate = this.from.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        String formattedToDate = this.to.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "[" + this.taskType + "][" + super.status + "] "
                + super.description
                + " (FROM: " + formattedFromDate
                + " TO: " + formattedToDate
                + ")";
    }
}
