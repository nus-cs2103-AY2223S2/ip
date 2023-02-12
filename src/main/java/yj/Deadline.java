package yj;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");

    public Deadline(String description, String by) {
        super(description);
        // Parse the by string as a LocalDateTime
        this.by = LocalDateTime.parse(by, dateTimeFormatter);
    }

    /**
     * Returns the formatted deadline of the deadline.
     *
     * @return deadline of the deadline formatted as MMM dd yyyy h:mma.
     */
    public String getBy() {
        return outputFormatter.format(by);
    }

    /**
     * Sets the deadline of the deadline and saves as a LocalDateTime.
     *
     * @param by deadline of the deadline in the format d/M/yyyy HHmm.
     */
    public void setBy(String by) {
        this.by = LocalDateTime.parse(by, dateTimeFormatter);
    }

    @Override
    public String toString() {
        // [D][ ] return book (by: June 6th)
        return "[D]" + " " + super.getStatusIcon() + " " + super.toString() + " (by: " + getBy() + ")";
    }

    /**
     * Returns the string representation of the deadline to be saved in the file.
     *
     * @return string representation of the deadline to be saved in the file.
     */
    @Override
    public String toSaveString() {
        // D | 0 | return book | June 6th
        return "D | " + (isDone ? 1 : 0) + " | " + super.toString() + " | " + by.format(dateTimeFormatter);
    }
}
