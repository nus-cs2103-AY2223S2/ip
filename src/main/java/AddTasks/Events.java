package AddTasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor for Events task.
     * @param description Name of the Event task.
     * @param from Date which the Event task starts, must be in dd/mm/yyyy format.
     * @param to Date which the Event task ends, must be in dd/mm/yyyy format.
     */
    public Events(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * String representation of the Event task.
     * @return String representation of the Event task with the date in the format of MMM d yyyy.
     */
    @Override
    public String toString() {
        String convertFrom = null;
        String convertTo = null;
        try {
            convertFrom = from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            convertTo = to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (NullPointerException e) {
        }
        return " " + "[E]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (From: " + convertFrom + " | To: " + convertTo + ")";
    }
}

