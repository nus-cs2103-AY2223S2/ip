package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Represents a Deadline task. A Deadline task has a description,
 * a 'by' timeframe, and can be marked as done.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate byDate;
    protected Optional<LocalTime> byTime;

    /**
     * Constructor for Deadline task, marked as undone.
     *
     * @param description Deadline task description.
     * @param by Deadline date/time.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        String[] split = by.split(" ");
        this.byDate = LocalDate.parse(split[0]);
        if (split.length > 1) {
            // Time is specified by user
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
            this.byTime = Optional.of(LocalTime.parse(split[1], formatter));
        } else {
            this.byTime = Optional.empty();
        }
    }

    /**
     * Constructor for Deadline task with boolean specified.
     *
     * @param description Deadline task description.
     * @param bool Indicates whether task is marked as done.
     * @param by Deadline date/time.
     */
    public Deadline(String description, boolean bool, String by) {
        super(description, bool);
        this.by = by;
        String[] split = by.split(" ");
        this.byDate = LocalDate.parse(split[0]);
        if (split.length > 1) {
            // Time is specified by user
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
            this.byTime = Optional.of(LocalTime.parse(split[1], formatter));
        } else {
            this.byTime = Optional.empty();
        }
    }

    /**
     * Returns a Deadline task marked as done.
     *
     * @return Deadline task marked as done.
     */
    @Override
    public Deadline markAsDone() {
        return new Deadline(description, true, by);
    }

    /**
     * Returns a Deadline task unmarked from being done.
     *
     * @return Deadline task unmarked from being done.
     */
    @Override
    public Deadline unmarkAsDone() {
        return new Deadline(description, by);
    }

    /**
     * Returns data for storage purposes.
     *
     * @return Data for storage purposes.
     */
    @Override
    public String getDataToSave() {
        return "D / " + getStatusNum() + " / " + getDesc() + " / " + by;
    }

    /**
     * Returns the details of the Deadline task.
     *
     * @return Details of the Deadline task.
     */
    @Override
    public String toString() {
        if (byTime.isEmpty()) {
            return "[D]" + super.toString() + " (by: "
                    + byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                    + byTime.get().format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
        }
    }
}
