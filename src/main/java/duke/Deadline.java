package duke;


import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline date. Inherits from the Task class.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    protected boolean hasTime;

    /**
     * Creates a deadline object with the given arguments.
     *
     * @param description description for the task
     * @param by deadline's due date and time
     * @param hasTime indicates if the given deadline includes a time
     */
    public Deadline(String description, LocalDateTime by, boolean hasTime) {
        super(description);
        this.by = by;
        this.hasTime = hasTime;
    }

    public String getBy() {
        if (hasTime) {
            return DateTimeParser.dateTimeToDisplayString(this.by);
        } else {
            return DateTimeParser.dateToDisplayString(this.by.toLocalDate());
        }
    }

    public void setBy(LocalDateTime by, boolean hasTime) {
        this.by = by;
        this.hasTime = hasTime;
    }

    /**
     * Returns the deadline due date and time in a format suitable for storage.
     * If the due date does not include a time, it returns a LocalDate. If it includes a time, it returns
     * a LocalDateTime.
     *
     * @return the deadline's due date and time in a format suitable for storage.
     */
    public String getByInStorageFormat() {
        if (hasTime) {
            return DateTimeParser.dateTimeToStorageString(this.by);
        } else {
            return DateTimeParser.dateToStorageString(this.by.toLocalDate());
        }
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " + this.getBy() + ")";
    }


    /**
     * Returns a Deadline object after parsing a Deadline's storage string produced by DateTimeParser's
     * dateTimeToStorageString or dateToStorageString that is split on the delimiter '/'. The format for the
     * Deadline's storage String is D/(isMarked)/(Description)/(Date).
     *
     * @param parts the split Deadline storage string
     * @return a Deadline object built from the parts array
     * @throws DateTimeParseException
     */
    public static Deadline parseDeadlineStringArray(String[] parts) throws DateTimeParseException {
        assert parts.length == 4;

        String taskDesc = parts[2];
        String byString = parts[3];

        String[] byParts = byString.split(" ");
        boolean hasTime = byParts.length == 2;

        Deadline task = new Deadline(taskDesc, DateTimeParser.parse(byString), hasTime);

        if (Integer.parseInt(parts[1]) == 1) {
            task.mark();
        }

        assert task != null;
        return task;
    }
}
