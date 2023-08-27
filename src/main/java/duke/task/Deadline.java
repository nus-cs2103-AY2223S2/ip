package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends Task {
    private String deadline;
    private LocalDateTime date;
    private boolean isDate;


    /**
     * Creates an instance of Deadline with a description and a deadline where an attempt
     * is made to parse into a LocalDate, else it remains as a String.
     *
     * @param description Describes the task.
     * @param deadline Represents the deadline of the task.
     */
    public Deadline(String description, String deadline) {
        super(description);

        // Checks if the deadline provided is a valid date
        this.isDate = false;
        for (DateTimeFormatter format: Task.DATETIME_FORMATS) {
            try {
                this.date = LocalDateTime.parse(deadline, format);
                this.isDate = true;
                break;
            } catch (DateTimeParseException ex) {

            }
        }
        if (!isDate) {
            for (DateTimeFormatter format: Task.DATE_FORMATS) {
                try {
                    this.date = LocalDate.parse(deadline, format).atStartOfDay();
                    this.isDate = true;
                    break;
                } catch (DateTimeParseException ex) {

                }
            }
        }
        this.deadline = deadline;
    }

    /**
     * String representation of the task with the deadline.
     * If the deadline is in LocalDate format, display as MMM dd yyyy.
     *
     * @return String representation of the task
     */
    @Override
    public String toString() {
        assert !this.isDate || (this.deadline != null);
        String dateToPrint = this.isDate ? this.date.format(DEFAULT_DATETIME_FORMAT) : this.deadline;
        return String.format("[D]%s (by: %s)", super.toString(), dateToPrint);
    }

    @Override
    public String toCsvString() {
        String dateToPrint = this.isDate ? this.date.format(DEFAULT_DATETIME_FORMAT) : this.deadline;
        return String.format("D,%s,%s", super.toCsvString(), dateToPrint);
    }
}
