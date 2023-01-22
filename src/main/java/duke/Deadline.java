/**
 * Represents a Deadline task. A Deadline task has a description,
 * a 'by' timeframe, and can be marked as done.
 */
package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Deadline extends Task {
    protected String by;
    protected LocalDate byDate;
    protected Optional<LocalTime> byTime;

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
     * Marks a Deadline task as done.
     * @return Deadline task marked as done.
     */
    @Override
    public Deadline markAsDone() {
        return new Deadline(description, true, by);
    }

    /**
     * Unmarks a Deadline task from being done.
     * @return Deadline task unmarked from being done.
     */
    @Override
    public Deadline unmarkAsDone() {
        return new Deadline(description, by);
    }

    /**
     * Returns data for storage purposes.
     * @return Data for storage purposes.
     */
    @Override
    public String getDataToSave() { return "D / " + getStatusNum() + " / " + getDesc() + " / " + by; }

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
